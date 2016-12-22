package com.mao.shop.service;

import java.util.Map;

import com.mao.shop.po.Customer;

public interface UserService {

	/**
	 * 保存注册的用户信息
	 * @param customer
	 */
	void saveUser(Customer customer);

	/**
	 * 查询用户名是否存在
	 * @param userName
	 * @return
	 */
	Boolean selectByName(String userName);

	/**
	 * 验证用户信息
	 * @param map 包含用户名和密码
	 * @return
	 */
	Customer selectUserByUserPass(Map<String, String> map);

}
