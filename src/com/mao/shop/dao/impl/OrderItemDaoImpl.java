package com.mao.shop.dao.impl;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mao.shop.dao.OrderItemDao;
import com.mao.shop.po.OrderItem;

@Repository
public class OrderItemDaoImpl extends SqlSessionDaoSupport implements OrderItemDao {

	@Autowired
	@Override
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		super.setSqlSessionFactory(sqlSessionFactory);
	}
	
	String ns = "com.mao.shop.mapper.OrderItemMapper.";

	@Override
	public void insert(OrderItem item) {
		getSqlSession().insert(ns+"insert", item);
	}
}
