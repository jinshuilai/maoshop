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

	// ȷ������ҳ��
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

	// �ύ����
	@RequestMapping("/submitOrder.do")
	public String submitOrder(String ship, Orders orders, OrderAddr orderAddr, HttpServletRequest request,
			HttpServletResponse response) {
		Customer customer = (Customer) request.getSession().getAttribute("user");
		if (customer == null) {
			return "redirect:/index";
		}
		// �����û��Ͷ������
		orders.setCustId(customer.getCid());
		orders.setOrderSn(MaoUtils.getOrderNo(customer.getCid()));
		BigDecimal totalPrice = new BigDecimal(0);
		// ���ö�����ַ
		if (!StringUtils.equals(ship, "add")) {
			CustShipaddr shipaddr = addrService.selectByShipId(new Integer(ship));
			orderAddr.copyShipAddr(shipaddr);
		}
		// ��װ������
		List<Cart> cartList = cartService.selectCart(request, response);
		List<OrderItem> itemList = new ArrayList<OrderItem>();
		for (Cart cart : cartList) {
			OrderItem item = new OrderItem();
			item.setSkuId(cart.getSkuId());
			item.setProductId(cart.getSku().getProduct().getProId());
			item.setProName(cart.getSku().getProduct().getPname());
			// ��װ�������
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
		// ������Ʒ�۸���˷�
		orders.setProductPrice(totalPrice);
		orders.setFreightFee(new BigDecimal(0));
		orders.setTotalPrice(totalPrice);

		// ���涩����Ϣ�����������䣬������ַ���������
		try {
			orderService.saveOrder(orders, orderAddr, itemList, request, response);
			request.setAttribute("order", orders);
		} catch (Exception e) {
			if (e instanceof StockException) {
				request.setAttribute("message", "�ܱ�Ǹ����治�㵼���µ�ʧ��");
				return "portal/info";
			} else {
				e.printStackTrace();
			}
		}
		return "redirect:toPay.do?sn=" + orders.getOrderSn();
	}

	// ֧��ҳ��
	@RequestMapping("/toPay.do")
	public String toPay(String sn, HttpSession session, Model model) {
		Customer customer = (Customer) session.getAttribute("user");
		Orders orders = orderService.selectBySN(sn, customer.getCid());
		if (orders != null) {
			if (orders.getStatus() != Orders.NO_PAY) {
				model.addAttribute("message", "�����Ѿ�֧������");
				return "portal/info";
			}
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(orders.getPlaceAt());
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			orders.setPlaceAt(calendar.getTime());
			model.addAttribute("order", orders);
			return "portal/order/payCase";
		} else {
			model.addAttribute("message", "û�в�ѯ����δ�����");
			return "portal/info";
		}

	}

	// ����֧��
	@RequestMapping("/pay.do")
	public void pay(String bank, Integer oid, HttpServletResponse response) throws UnsupportedEncodingException {
		Orders orders = orderService.selectDetailByOid(oid);
		if (orders.getStatus() == Orders.NO_PAY) {
			String partner = "123";// �̻����
			String subject = orders.getItemList().get(0).getProName();// ��Ʒ����
			if (orders.getItemList().size() > 1) {
				subject = subject + " �ȶ��";
			}
			subject = "product";
			String body = "maoshop";// ��Ʒ����
			String out_trade_no = orders.getOrderSn();// ������
			String total_fee = orders.getTotalPrice().toString();// �ܽ��
			String seller_email = "maoshop";// ��������
			String return_url = MaoUtils.readProp("pay_callback");// �ص���ַ
			String key = MaoUtils.readProp("pay_key");// ֧����Կ
			String sign = new MD5().GetMD5Code(total_fee + partner + out_trade_no + subject + key);// ����ǩ��
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

	// ֧������ص�
	@RequestMapping("/payResult.do")
	public String payResult(HttpSession session, HttpServletRequest request, Model model) {
		String out_trade_no = request.getParameter("out_trade_no");// ������
		String returncode = request.getParameter("returncode");// ������
		String total_fee = request.getParameter("total_fee");// ֧�����
		String sign = request.getParameter("sign");// ����ǩ��
		String privateSign = new MD5().GetMD5Code(out_trade_no + returncode + total_fee + MaoUtils.readProp("pay_key"));
		if (!sign.equals(privateSign)) { // ǩ��У��
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
			// ֪ͨ��̨���¶���
			websocketEndPoint().sendMessageToUsers(new TextMessage("���µĶ���δ����"));
		} else {
			model.addAttribute("result", "error2");
		}
		return "portal/order/payResult";
	}

}
