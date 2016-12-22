package com.mao.shop.dao.impl;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mao.shop.dao.SnapshotDao;
import com.mao.shop.po.ProductSnapshot;

@Repository
public class SnapshotDaoImpl extends SqlSessionDaoSupport implements SnapshotDao {

	@Autowired
	@Override
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		super.setSqlSessionFactory(sqlSessionFactory);
	}
	
	String ns = "com.mao.shop.mapper.ProductSnapshotMapper.";

	@Override
	public void save(ProductSnapshot snapshot) {
		getSqlSession().insert(ns+"insert", snapshot);
	}

	@Override
	public ProductSnapshot select(Integer id) {
		
		return getSqlSession().selectOne(ns+"selectByPrimaryKey", id);
	}
}
