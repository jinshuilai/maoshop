package com.mao.shop.dao;

import com.mao.shop.po.OrderAddr;

public interface OrderAddrDao {

	/**
	 * ���涩����ַ
	 * @param orderAddr
	 */
	void insert(OrderAddr orderAddr);

	/**
	 * ��ѯ�ջ���ַ
	 * @param id
	 * @return
	 */
	OrderAddr selectByOrderId(Integer id);

}
