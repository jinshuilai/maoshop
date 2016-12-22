package com.mao.shop.dao;

import java.util.Map;

import com.mao.shop.po.Customer;

public interface UserDao {

	/**
	 * 保存用户信息
	 * @param customer
	 */
	void insertUser(Customer customer);

	/**
	 * 查询用户名是否存在
	 * @param userName
	 * @return
	 */
	Boolean selectByName(String userName);

	/**
	 * 用户登录验证
	 * @param map 包含username 和password
	 * @return
	 */
	Customer selectByUserPass(Map<String, String> map);

}
