package com.mao.shop.controller.portal;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mao.shop.po.Cart;
import com.mao.shop.po.ProductSku;
import com.mao.shop.service.CartService;
import com.mao.shop.service.SkuService;
import com.mao.shop.utils.MaoUtils;

@Controller
@RequestMapping("/cart")
public class CartController {

	@Autowired
	private SkuService skuService;
	@Autowired
	private CartService cartService;

	//加入购物车
	@RequestMapping("/addCart.do")
	public void addCart(Integer skuId, Integer quantity, HttpServletResponse response, HttpServletRequest request,
			PrintWriter out) {
		cartService.addCart(skuId, quantity, request, response);
		out.write("success");
	}

	//验证库存数量
	@RequestMapping("/validStock.do")
	public void validStock(Integer skuId, Integer quantity, PrintWriter out) {
		String result = "yes";
		JSONObject jo = new JSONObject();
		ProductSku sku = skuService.selectById(skuId);
		if (sku.getStock() < quantity) {
			result = "no";
			jo.accumulate("stock", sku.getStock());
		}
		jo.accumulate("result", result);
		out.write(jo.toString());
	}

	//查询购物车
	@RequestMapping("/listCart.do")
	public String listCart(HttpServletRequest request, HttpServletResponse response, Model model) {
		List<Cart> carts = cartService.selectCart(request, response);
		model.addAttribute("cartList", carts);
		Integer itemNum = 0;
		BigDecimal totalPrice = new BigDecimal(0);
		for (Cart cart : carts) {
			itemNum = cart.getQuantity() + itemNum;
			totalPrice = totalPrice.add(cart.getSku().getShopPrice().
					multiply(new BigDecimal(cart.getQuantity())));
		}
		model.addAttribute("itemNum", itemNum);
		model.addAttribute("totalPrice",totalPrice);
		
		return "portal/product/cart";
	}
	
	//删除购物车商品
	@RequestMapping("/deleteCart.do")
	public String deleteCart(Integer skuId,HttpServletRequest request,HttpServletResponse response){
		cartService.deleteCart(skuId,request,response);
		return "redirect:listCart.do";
	}
	
	//修改购物项数量
	@RequestMapping("/updateCartNum.do")
	public String updateCartNum(Integer skuId,Integer quantity,HttpServletRequest request,HttpServletResponse response) {
		cartService.updateCart(skuId,quantity,request,response);
		return "redirect:listCart.do";
	}
	
	//清空购物车
	@RequestMapping("/clearCart.do")
	public String clearCart(HttpServletRequest request,HttpServletResponse response) {
		cartService.clearCart(request,response);
		return "redirect:listCart.do";
	}
	
	//验证购物车
	@RequestMapping("/validCart.do")
	public void validCart(HttpServletRequest request,HttpServletResponse response) {
		String result = cartService.validCart(request,response);
		MaoUtils.printJSON(result, response);
	}
	
}
