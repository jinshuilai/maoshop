package com.mao.shop.service;

import java.util.Map;

import com.mao.shop.po.Customer;

public interface UserService {

	/**
	 * ����ע����û���Ϣ
	 * @param customer
	 */
	void saveUser(Customer customer);

	/**
	 * ��ѯ�û����Ƿ����
	 * @param userName
	 * @return
	 */
	Boolean selectByName(String userName);

	/**
	 * ��֤�û���Ϣ
	 * @param map �����û���������
	 * @return
	 */
	Customer selectUserByUserPass(Map<String, String> map);

}
