package com.mao.shop.dao;

import java.util.Map;

import com.mao.shop.po.Customer;

public interface UserDao {

	/**
	 * �����û���Ϣ
	 * @param customer
	 */
	void insertUser(Customer customer);

	/**
	 * ��ѯ�û����Ƿ����
	 * @param userName
	 * @return
	 */
	Boolean selectByName(String userName);

	/**
	 * �û���¼��֤
	 * @param map ����username ��password
	 * @return
	 */
	Customer selectByUserPass(Map<String, String> map);

}
