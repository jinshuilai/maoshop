package com.mao.shop.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mao.shop.dao.UserDao;
import com.mao.shop.po.Customer;

@Repository
public class UserDaoImpl extends SqlSessionDaoSupport implements UserDao {

	@Autowired
	@Override
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		super.setSqlSessionFactory(sqlSessionFactory);
	}
	
	String ns = "com.mao.shop.mapper.CustomerMapper.";

	@Override
	public void insertUser(Customer customer) {
		getSqlSession().insert(ns+"insert", customer);
	}

	@Override
	public Boolean selectByName(String userName) {
		List<Customer> customers = getSqlSession().selectList(ns+"selectByName", userName);
		if (customers != null && customers.size() > 0) {
			return false;
		}
		return true;
	}

	@Override
	public Customer selectByUserPass(Map<String, String> map) {
		
		return getSqlSession().selectOne(ns+"selectByUserPass", map);
	}
}
