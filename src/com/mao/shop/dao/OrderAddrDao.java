package com.mao.shop.dao;

import com.mao.shop.po.OrderAddr;

public interface OrderAddrDao {

	/**
	 * 保存订单地址
	 * @param orderAddr
	 */
	void insert(OrderAddr orderAddr);

	/**
	 * 查询收货地址
	 * @param id
	 * @return
	 */
	OrderAddr selectByOrderId(Integer id);

}
