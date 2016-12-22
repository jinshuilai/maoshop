package com.mao.shop.dao.impl;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mao.shop.dao.OrderAddrDao;
import com.mao.shop.po.OrderAddr;

@Repository
public class OrderAddrDaoImpl extends SqlSessionDaoSupport implements OrderAddrDao {

	@Autowired
	@Override
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		super.setSqlSessionFactory(sqlSessionFactory);
	}
	
	String ns = "com.mao.shop.mapper.OrderAddrMapper.";

	@Override
	public void insert(OrderAddr orderAddr) {
		getSqlSession().insert(ns+"insert", orderAddr);
	}

	@Override
	public OrderAddr selectByOrderId(Integer id) {
		return getSqlSession().selectOne(ns+"selectByOrderId", id);
	}

	
}
