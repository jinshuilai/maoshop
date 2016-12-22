package com.mao.shop.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mao.shop.dao.FeedbackDao;
import com.mao.shop.po.FeedbackLever;
import com.mao.shop.po.ProductFeedback;

@Repository
public class FeedbackDaoImpl extends SqlSessionDaoSupport implements FeedbackDao {

	@Autowired
	@Override
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		super.setSqlSessionFactory(sqlSessionFactory);
	}
	
	String ns = "com.mao.shop.mapper.ProductFeedbackMapper.";

	@Override
	public void insert(ProductFeedback pf) {
		getSqlSession().insert(ns+"insert", pf);
	}

	@Override
	public int selectCount(Integer pid,Integer level) {
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("pid", pid);
		map.put("fblever", level);
		return getSqlSession().selectOne(ns+"selectCount", map);
	}

	@Override
	public List<ProductFeedback> selectByPage(int begin, int i, Integer pid,Integer level) {
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("begin", begin);
		map.put("size", i);
		map.put("pid", pid);
		map.put("fblever", level);
		return getSqlSession().selectList(ns+"selectByPage", map);
	}

	@Override
	public FeedbackLever selectLeverNum(Integer pid) {
		return getSqlSession().selectOne(ns+"selectFeedNum", pid);
	}

	@Override
	public List<ProductFeedback> selectNewFeedback() {
		
		return getSqlSession().selectList(ns+"selectNewFeedback");
	}

	@Override
	public ProductFeedback selectByItemId(Integer id, Integer cid) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("itemId", id);
		map.put("custId", cid);
		return getSqlSession().selectOne(ns+"selectByItemId", map);
	}
}
