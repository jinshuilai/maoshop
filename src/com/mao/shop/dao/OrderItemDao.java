package com.mao.shop.dao;

import com.mao.shop.po.OrderItem;

public interface OrderItemDao {

	//保存订单项信息
	void insert(OrderItem item);

}
