package com.mao.shop.controller.portal;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.socket.TextMessage;

import com.mao.shop.exception.StockException;
import com.mao.shop.po.Cart;
import com.mao.shop.po.CustShipaddr;
import com.mao.shop.po.Customer;
import com.mao.shop.po.OrderAddr;
import com.mao.shop.po.OrderItem;
import com.mao.shop.po.Orders;
import com.mao.shop.po.ProductSpec;
import com.mao.shop.po.Region;
import com.mao.shop.service.CartService;
import com.mao.shop.service.OrderService;
import com.mao.shop.service.ShipAddrService;
import com.mao.shop.utils.MD5;
import com.mao.shop.utils.MaoUtils;
import com.mao.shop.websocket.WebsocketEndPoint;

@Controller
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderService orderService;
	@Autowired
	private ShipAddrService addrService;
	@Autowired
	private CartService cartService;

	@Bean
	public WebsocketEndPoint websocketEndPoint() {
		return new WebsocketEndPoint();
	}

	// 确定订单页面
	@RequestMapping("/toSubmitOrder.do")
	public String toSubmitOrder(HttpServletRequest request, HttpServletResponse response, Model model) {
		Customer customer = (Customer) request.getSession().getAttribute("user");
		if (customer == null) {
			return "redirect:/index";
		}
		List<CustShipaddr> addrs = addrService.selectByUserId(customer.getCid());
		model.addAttribute("addrList", addrs);
		List<Region> rList = addrService.selectProvince();
		model.addAttribute("rList", rList);
		List<Cart> cartList = cartService.selectCart(request, response);
		model.addAttribute("cartList", cartList);
		Integer itemNum = 0;
		BigDecimal totalPrice = new BigDecimal(0);
		for (Cart cart : cartList) {
			itemNum = cart.getQuantity() + itemNum;
			totalPrice = totalPrice.add(cart.getSku().getShopPrice().multiply(new BigDecimal(cart.getQuantity())));
		}
		model.addAttribute("itemNum", itemNum);
		model.addAttribute("totalPrice", totalPrice);
		return "portal/order/confirmProduct";
	}

	// 提交订单
	@RequestMapping("/submitOrder.do")
	public String submitOrder(String ship, Orders orders, OrderAddr orderAddr, HttpServletRequest request,
			HttpServletResponse response) {
		Customer customer = (Customer) request.getSession().getAttribute("user");
		if (customer == null) {
			return "redirect:/index";
		}
		// 设置用户和订单编号
		orders.setCustId(customer.getCid());
		orders.setOrderSn(MaoUtils.getOrderNo(customer.getCid()));
		BigDecimal totalPrice = new BigDecimal(0);
		// 设置订单地址
		if (!StringUtils.equals(ship, "add")) {
			CustShipaddr shipaddr = addrService.selectByShipId(new Integer(ship));
			orderAddr.copyShipAddr(shipaddr);
		}
		// 组装订单项
		List<Cart> cartList = cartService.selectCart(request, response);
		List<OrderItem> itemList = new ArrayList<OrderItem>();
		for (Cart cart : cartList) {
			OrderItem item = new OrderItem();
			item.setSkuId(cart.getSkuId());
			item.setProductId(cart.getSku().getProduct().getProId());
			item.setProName(cart.getSku().getProduct().getPname());
			// 组装规格属性
			String specStr = "";
			List<ProductSpec> specList = cart.getSku().getSpecList();
			for (ProductSpec spec : specList) {
				specStr = specStr + spec.getAttrvalue() + ",";
			}
			specStr = specStr.substring(0, specStr.length() - 1);
			item.setSkuSpec(specStr);
			item.setPrice(cart.getSku().getShopPrice());
			item.setQuantity(cart.getQuantity());

			itemList.add(item);
			totalPrice = totalPrice.add(cart.getSku().getShopPrice().multiply(new BigDecimal(cart.getQuantity())));
		}
		// 设置商品价格和运费
		orders.setProductPrice(totalPrice);
		orders.setFreightFee(new BigDecimal(0));
		orders.setTotalPrice(totalPrice);

		// 保存订单信息，包括订单变，订单地址表，订单项表
		try {
			orderService.saveOrder(orders, orderAddr, itemList, request, response);
			request.setAttribute("order", orders);
		} catch (Exception e) {
			if (e instanceof StockException) {
				request.setAttribute("message", "很抱歉，库存不足导致下单失败");
				return "portal/info";
			} else {
				e.printStackTrace();
			}
		}
		return "redirect:toPay.do?sn=" + orders.getOrderSn();
	}

	// 支付页面
	@RequestMapping("/toPay.do")
	public String toPay(String sn, HttpSession session, Model model) {
		Customer customer = (Customer) session.getAttribute("user");
		Orders orders = orderService.selectBySN(sn, customer.getCid());
		if (orders != null) {
			if (orders.getStatus() != Orders.NO_PAY) {
				model.addAttribute("message", "订单已经支付过了");
				return "portal/info";
			}
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(orders.getPlaceAt());
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			orders.setPlaceAt(calendar.getTime());
			model.addAttribute("order", orders);
			return "portal/order/payCase";
		} else {
			model.addAttribute("message", "没有查询到该未付款订单");
			return "portal/info";
		}

	}

	// 进行支付
	@RequestMapping("/pay.do")
	public void pay(String bank, Integer oid, HttpServletResponse response) throws UnsupportedEncodingException {
		Orders orders = orderService.selectDetailByOid(oid);
		if (orders.getStatus() == Orders.NO_PAY) {
			String partner = "123";// 商户编号
			String subject = orders.getItemList().get(0).getProName();// 商品名称
			if (orders.getItemList().size() > 1) {
				subject = subject + " 等多件";
			}
			subject = "product";
			String body = "maoshop";// 商品描述
			String out_trade_no = orders.getOrderSn();// 订单号
			String total_fee = orders.getTotalPrice().toString();// 总金额
			String seller_email = "maoshop";// 卖家邮箱
			String return_url = MaoUtils.readProp("pay_callback");// 回调地址
			String key = MaoUtils.readProp("pay_key");// 支付密钥
			String sign = new MD5().GetMD5Code(total_fee + partner + out_trade_no + subject + key);// 数字签名
			// subject = URLEncoder.encode(subject,"utf-8");
			try {
				response.sendRedirect("http://paytest.rupeng.cn/AliPay/PayGate.ashx?partner=" + partner + "&return_url="
						+ return_url + "&subject=" + subject + "&body=" + body + "&out_trade_no=" + out_trade_no
						+ "&total_fee=" + total_fee + "&seller_email=" + seller_email + "&sign=" + sign);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	// 支付结果回调
	@RequestMapping("/payResult.do")
	public String payResult(HttpSession session, HttpServletRequest request, Model model) {
		String out_trade_no = request.getParameter("out_trade_no");// 订单号
		String returncode = request.getParameter("returncode");// 返回码
		String total_fee = request.getParameter("total_fee");// 支付金额
		String sign = request.getParameter("sign");// 数字签名
		String privateSign = new MD5().GetMD5Code(out_trade_no + returncode + total_fee + MaoUtils.readProp("pay_key"));
		if (!sign.equals(privateSign)) { // 签名校验
			model.addAttribute("result", "error1");
			return "portal/order/payResult";
		}
		if (returncode.equals("ok")) {
			Customer customer = (Customer) session.getAttribute("user");
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("status", Orders.PAY);
			map.put("userId", customer.getCid());
			map.put("sn", out_trade_no);
			map.put("paytime", new Date());
			orderService.updateStatus(map);
			model.addAttribute("result", "success");
			model.addAttribute("sn", out_trade_no);
			model.addAttribute("price", total_fee);
			// 通知后台有新订单
			websocketEndPoint().sendMessageToUsers(new TextMessage("有新的订单未处理"));
		} else {
			model.addAttribute("result", "error2");
		}
		return "portal/order/payResult";
	}

}
