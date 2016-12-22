package com.mao.shop.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mao.shop.po.Cart;

public interface CartService {

	/**
	 * 加入购物车
	 * @param skuId
	 * @param quantity
	 * @param request
	 * @param response
	 */
	void addCart(Integer skuId, Integer quantity, HttpServletRequest request, HttpServletResponse response);

	/**
	 * 查询购物车
	 * @param request
	 * @param response
	 * @return
	 */
	List<Cart> selectCart(HttpServletRequest request, HttpServletResponse response);

	/**
	 * 删除购物项
	 * @param skuId
	 * @param request
	 * @param response
	 */
	void deleteCart(Integer skuId, HttpServletRequest request, HttpServletResponse response);

	/**
	 * 修改购物项数量
	 * @param skuId
	 * @param quantity
	 * @param request
	 * @param response
	 */
	void updateCart(Integer skuId, Integer quantity, HttpServletRequest request, HttpServletResponse response);

	/**
	 * 清空购物车
	 * @param request
	 * @param response
	 */
	void clearCart(HttpServletRequest request, HttpServletResponse response);

	/**
	 * 验证购物车
	 * @param request
	 * @param response
	 * @return
	 */
	String validCart(HttpServletRequest request, HttpServletResponse response);

}
