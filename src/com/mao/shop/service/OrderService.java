package com.mao.shop.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mao.shop.po.OrderAddr;
import com.mao.shop.po.OrderItem;
import com.mao.shop.po.Orders;
import com.mao.shop.po.ProductSnapshot;
import com.mao.shop.utils.PageBean;

public interface OrderService {

	/**
	 * 保存订单信息
	 * @param orders
	 * @param orderAddr
	 * @param itemList
	 * @param request
	 * @param response
	 */
	void saveOrder(Orders orders, OrderAddr orderAddr, List<OrderItem> itemList, HttpServletRequest request,
			HttpServletResponse response);

	/**
	 * 根据订单编号查询订单
	 * @param sn
	 * @param integer 
	 * @return
	 */
	Orders selectBySN(String sn, Integer integer);

	/**
	 * 根据订单id查询订单详情，包含订单项
	 * @param oid
	 * @return
	 */
	Orders selectDetailByOid(Integer oid);

	/**
	 * 查询用户的全部订单
	 * @param cid
	 * @param page 
	 * @param s 订单状态
	 * @return
	 */
	PageBean selectAllByUserId(Integer cid, Integer page, Integer s);

	/**
	 * 更改订单状态
	 * @param sn 订单序列号
	 * @param status 变更为的状态 
	 * @param uid 用户id
	 */
	void updateStatus(Map<String, Object> map);

	/**
	 * 查询交易快照
	 * @param id
	 * @return
	 */
	ProductSnapshot selectSnapshot(Integer id);

	/**
	 * 查询所有订单
	 * @param page
	 * @param integer
	 * @param type
	 * @return
	 */
	PageBean selectAllByPage(Integer page, Integer integer, Integer type);

	/**
	 * 查询订单项
	 * @param id
	 * @return
	 */
	Orders selectItemByOrderId(Integer id);

	/**
	 * 查询收货地址
	 * @param id
	 * @return
	 */
	OrderAddr selectShipByOrderId(Integer id);

	/**
	 * 查询已付款的订单
	 * @return
	 */
	int selectNoDealCount();

}
