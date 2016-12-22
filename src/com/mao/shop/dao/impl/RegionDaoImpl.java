package com.mao.shop.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mao.shop.dao.RegionDao;
import com.mao.shop.po.Region;

@Repository
public class RegionDaoImpl extends SqlSessionDaoSupport implements RegionDao {

	@Autowired
	@Override
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		super.setSqlSessionFactory(sqlSessionFactory);
	}
	
	String ns = "com.mao.shop.mapper.RegionMapper.";

	@Override
	public List<Region> selectProvince() {
		
		return getSqlSession().selectList(ns+"selectProvince");
	}

	@Override
	public List<Region> selectByParentId(Short parentId) {
		
		return getSqlSession().selectList(ns+"selectByPid", parentId);
	}
}
