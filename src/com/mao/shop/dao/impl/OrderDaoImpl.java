package com.mao.shop.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mao.shop.dao.OrderDao;
import com.mao.shop.po.Orders;

@Repository
public class OrderDaoImpl extends SqlSessionDaoSupport implements OrderDao {

	@Autowired
	@Override
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		super.setSqlSessionFactory(sqlSessionFactory);
	}
	
	String ns = "com.mao.shop.mapper.OrdersMapper.";

	@Override
	public void insert(Orders orders) {
		getSqlSession().insert(ns+"insert", orders);
	}

	@Override
	public Orders selectBySN(String sn, Integer userId) {
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("sn", sn);
		map.put("userId", userId);
		return getSqlSession().selectOne(ns+"selectBySN", map);
	}

	@Override
	public Orders selectDetailById(Integer oid) {
		return getSqlSession().selectOne(ns+"selectDetail", oid);
	}

	@Override
	public int selectCount(Integer cid,Integer s) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("cid", cid);
		map.put("stat", s);
		return getSqlSession().selectOne(ns+"selectCount", map);
	}

	@Override
	public List<Orders> selectByPage(int begin, int pageSize, Integer cid,Integer s) {
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("begin", begin);
		map.put("size", pageSize);
		map.put("userId", cid);
		map.put("stat", s);
		return getSqlSession().selectList(ns+"selectByPage", map);
	}

	@Override
	public void updateStatus(Map<String, Object> map) {
		getSqlSession().update(ns+"updateStatus", map);
	}

	@Override
	public int selectAllCount(Integer type) {
		if (type.intValue() == 1) {
			return getSqlSession().selectOne(ns+"selectAllCount",type);
		}else{
			return getSqlSession().selectOne(ns+"selectAllOther",type);
		}
	}

	@Override
	public List<Orders> selectAllByPage(int begin, int pageSize, Integer type) {
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("begin", begin);
		map.put("size", pageSize);
		map.put("type", type);
		if (type.intValue() == 1) {
			return getSqlSession().selectList(ns+"selectAllByPage", map);
		}else {
			return getSqlSession().selectList(ns+"selectOtherByPage", map);
		}
	}

	@Override
	public int selectCountByStatus(Short status) {
		
		return getSqlSession().selectOne(ns+"selectCountByStatus", status);
	}
}
