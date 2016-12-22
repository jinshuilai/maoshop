package com.mao.shop.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mao.shop.po.Cart;

public interface CartService {

	/**
	 * ���빺�ﳵ
	 * @param skuId
	 * @param quantity
	 * @param request
	 * @param response
	 */
	void addCart(Integer skuId, Integer quantity, HttpServletRequest request, HttpServletResponse response);

	/**
	 * ��ѯ���ﳵ
	 * @param request
	 * @param response
	 * @return
	 */
	List<Cart> selectCart(HttpServletRequest request, HttpServletResponse response);

	/**
	 * ɾ��������
	 * @param skuId
	 * @param request
	 * @param response
	 */
	void deleteCart(Integer skuId, HttpServletRequest request, HttpServletResponse response);

	/**
	 * �޸Ĺ���������
	 * @param skuId
	 * @param quantity
	 * @param request
	 * @param response
	 */
	void updateCart(Integer skuId, Integer quantity, HttpServletRequest request, HttpServletResponse response);

	/**
	 * ��չ��ﳵ
	 * @param request
	 * @param response
	 */
	void clearCart(HttpServletRequest request, HttpServletResponse response);

	/**
	 * ��֤���ﳵ
	 * @param request
	 * @param response
	 * @return
	 */
	String validCart(HttpServletRequest request, HttpServletResponse response);

}
