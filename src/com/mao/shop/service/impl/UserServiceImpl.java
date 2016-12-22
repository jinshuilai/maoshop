package com.mao.shop.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mao.shop.dao.UserDao;
import com.mao.shop.po.Customer;
import com.mao.shop.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Override
	public void saveUser(Customer customer) {
		userDao.insertUser(customer);
	}

	@Override
	public Boolean selectByName(String userName) {
		
		return userDao.selectByName(userName);
	}

	@Override
	public Customer selectUserByUserPass(Map<String, String> map) {
		
		return userDao.selectByUserPass(map);
	}
}
