package com.mao.shop.service.impl;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.mao.shop.dao.SkuDao;
import com.mao.shop.po.Cart;
import com.mao.shop.po.ProductSku;
import com.mao.shop.po.ProductSpec;
import com.mao.shop.service.CartService;
import com.mao.shop.utils.MaoUtils;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private SkuDao skuDao;

	@Override
	public void addCart(Integer skuId, Integer quantity, HttpServletRequest request, HttpServletResponse response) {
		// cookie list对购物数据操作
		List<Cart> cartList = new ArrayList<Cart>();
		// 获取cookie的key
		String cartKey = MaoUtils.readProp("cookie_cartKey");

		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		// 拿到所有的cookie
		Cookie[] cookies = request.getCookies();
		// 如果cookie不为空且有值
		if (cookies != null && cookies.length > 0) {
			for (Cookie cookie : cookies) {
				String cookieKey = cookie.getName();
				// 判断是否是cart的cookiekey
				if (StringUtils.equals(cartKey, cookieKey)) {
					String cartVal = cookie.getValue();
					// base64解码
					cartVal = URLDecoder.decode(cartVal);
					// cookie转换json
					// 转成java对象
					cartList = gson.fromJson(cartVal, new TypeToken<List<Cart>>() {
					}.getType());
					boolean isExsit = false;
					// 如果商品已存在
					for (Cart cart : cartList) {
						if (cart.getSkuId().intValue() == skuId.intValue()) {
							cart.setQuantity(cart.getQuantity() + quantity);
							isExsit = true;
							break;
						}
					}
					// 商品不存在
					if (!isExsit) {
						Cart cart = new Cart();
						cart.setSkuId(skuId);
						cart.setQuantity(quantity);
						cartList.add(cart);
					}
				}
			}
		}
		// 没有购物车cookie
		if (cartList.size() == 0) {
			Cart cart = new Cart();
			cart.setSkuId(skuId);
			cart.setQuantity(quantity);
			cartList.add(cart);
		}
		// java对象转换成json
		String result = gson.toJson(cartList);
		result = URLDecoder.decode(result);
		Cookie cookie = new Cookie(cartKey, result);
		cookie.setMaxAge(Integer.MAX_VALUE);
		cookie.setPath("/");
		response.addCookie(cookie);
	}

	@Override
	public List<Cart> selectCart(HttpServletRequest request, HttpServletResponse response) {
		// cookie list对购物数据操作
		List<Cart> cartList = new ArrayList<Cart>();
		// 获取cookie的key
		String cartKey = MaoUtils.readProp("cookie_cartKey");

		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		// 拿到所有的cookie
		Cookie[] cookies = request.getCookies();
		// 如果cookie不为空且有值
		if (cookies != null && cookies.length > 0) {
			for (Cookie cookie : cookies) {
				String cookieKey = cookie.getName();
				// 判断是否是cart的cookiekey
				if (StringUtils.equals(cartKey, cookieKey)) {
					String cartVal = cookie.getValue();
					// base64解码
					cartVal = URLDecoder.decode(cartVal);
					// cookie转换json
					// 转成java对象
					cartList = gson.fromJson(cartVal, new TypeToken<List<Cart>>() {
					}.getType());
					
					for (Cart cart : cartList) {
						ProductSku sku = skuDao.getSkuDetail(cart.getSkuId());
						cart.setSku(sku);
					}
				}
			}
		}
		
		return cartList;
	}

	@Override
	public void deleteCart(Integer skuId, HttpServletRequest request, HttpServletResponse response) {
		// cookie list对购物数据操作
		List<Cart> cartList = new ArrayList<Cart>();
		// 获取cookie的key
		String cartKey = MaoUtils.readProp("cookie_cartKey");

		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		// 拿到所有的cookie
		Cookie[] cookies = request.getCookies();
		// 如果cookie不为空且有值
		if (cookies != null && cookies.length > 0) {
			for (Cookie cookie : cookies) {
				String cookieKey = cookie.getName();
				// 判断是否是cart的cookiekey
				if (StringUtils.equals(cartKey, cookieKey)) {
					String cartVal = cookie.getValue();
					// base64解码
					cartVal = URLDecoder.decode(cartVal);
					// cookie转换json
					// 转成java对象
					cartList = gson.fromJson(cartVal, new TypeToken<List<Cart>>() {
					}.getType());
					
					for (int i = 0;i < cartList.size();i++) {
						Cart cart = cartList.get(i);
						if (cart.getSkuId().intValue() == skuId.intValue()) {
							cartList.remove(cart);
						}
					}
				}
			}
		}
		// java对象转换成json
		String result = gson.toJson(cartList);
		result = URLDecoder.decode(result);
		Cookie cookie = new Cookie(cartKey, result);
		cookie.setMaxAge(Integer.MAX_VALUE);
		cookie.setPath("/");
		response.addCookie(cookie);
	}

	@Override
	public void updateCart(Integer skuId, Integer quantity, HttpServletRequest request, HttpServletResponse response) {
		// cookie list对购物数据操作
		List<Cart> cartList = new ArrayList<Cart>();
		// 获取cookie的key
		String cartKey = MaoUtils.readProp("cookie_cartKey");

		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		// 拿到所有的cookie
		Cookie[] cookies = request.getCookies();
		// 如果cookie不为空且有值
		if (cookies != null && cookies.length > 0) {
			for (Cookie cookie : cookies) {
				String cookieKey = cookie.getName();
				// 判断是否是cart的cookiekey
				if (StringUtils.equals(cartKey, cookieKey)) {
					String cartVal = cookie.getValue();
					// base64解码
					cartVal = URLDecoder.decode(cartVal);
					// cookie转换json
					// 转成java对象
					cartList = gson.fromJson(cartVal, new TypeToken<List<Cart>>() {
					}.getType());
					
					for (Cart cart : cartList) {
						if (cart.getSkuId().intValue() == skuId.intValue()) {
							cart.setQuantity(quantity);
							break;
						}
					}
				}
			}
		}
		// java对象转换成json
		String result = gson.toJson(cartList);
		result = URLDecoder.decode(result);
		Cookie cookie = new Cookie(cartKey, result);
		cookie.setMaxAge(Integer.MAX_VALUE);
		cookie.setPath("/");
		response.addCookie(cookie);
		
	}

	@Override
	public void clearCart(HttpServletRequest request, HttpServletResponse response) {
		// cookie list对购物数据操作
		List<Cart> cartList = new ArrayList<Cart>();
		// 获取cookie的key
		String cartKey = MaoUtils.readProp("cookie_cartKey");

		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		// 拿到所有的cookie
		Cookie[] cookies = request.getCookies();
		// 如果cookie不为空且有值
		if (cookies != null && cookies.length > 0) {
			for (Cookie cookie : cookies) {
				String cookieKey = cookie.getName();
				// 判断是否是cart的cookiekey
				if (StringUtils.equals(cartKey, cookieKey)) {
					String cartVal = cookie.getValue();
					// base64解码
					cartVal = URLDecoder.decode(cartVal);
					// cookie转换json
					// 转成java对象
					cartList = gson.fromJson(cartVal, new TypeToken<List<Cart>>() {
					}.getType());
					
					cartList.clear();
				}
			}
		}
		// java对象转换成json
		String result = gson.toJson(cartList);
		result = URLDecoder.decode(result);
		Cookie cookie = new Cookie(cartKey, result);
		cookie.setMaxAge(Integer.MAX_VALUE);
		cookie.setPath("/");
		response.addCookie(cookie);
		
	}

	@Override
	public String validCart(HttpServletRequest request, HttpServletResponse response) {
		String result = "success";
		
		// cookie list对购物数据操作
		List<Cart> cartList = new ArrayList<Cart>();
		// 获取cookie的key
		String cartKey = MaoUtils.readProp("cookie_cartKey");

		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		// 拿到所有的cookie
		Cookie[] cookies = request.getCookies();
		// 如果cookie不为空且有值
		if (cookies != null && cookies.length > 0) {
			for (Cookie cookie : cookies) {
				String cookieKey = cookie.getName();
				// 判断是否是cart的cookiekey
				if (StringUtils.equals(cartKey, cookieKey)) {
					String cartVal = cookie.getValue();
					// base64解码
					cartVal = URLDecoder.decode(cartVal);
					// cookie转换json
					// 转成java对象
					cartList = gson.fromJson(cartVal, new TypeToken<List<Cart>>() {
					}.getType());
					
					for (Cart cart : cartList) {
						ProductSku sku = skuDao.getSkuDetail(cart.getSkuId());
						if (cart.getQuantity().intValue() > sku.getStock().intValue()) {
							result = "";
							result = result + sku.getProduct().getPname();
							List<ProductSpec> specList = sku.getSpecList();
							for (ProductSpec spec : specList) {
								result = result + spec.getAttrvalue();
							}
							result = result + "库存不足" + cart.getQuantity() +
									"实际库存为:" + sku.getStock();
							break;
						}
					}
				}
			}
		}
		return result;
	}

}
