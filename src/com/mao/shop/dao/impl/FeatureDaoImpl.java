package com.mao.shop.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mao.shop.dao.FeatureDao;
import com.mao.shop.po.ProductAttr;
import com.mao.shop.po.ProductAttrValue;

@Repository
public class FeatureDaoImpl extends SqlSessionDaoSupport implements FeatureDao {

	@Autowired
	@Override
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		super.setSqlSessionFactory(sqlSessionFactory);
	}
	
	String ns = "com.mao.shop.mapper.ProductAttrMapper.";
	String nsv = "com.mao.shop.mapper.ProductAttrValueMapper.";

	@Override
	public int selectFeatureCount() {
		
		return getSqlSession().selectOne(ns+"selectCount");
	}

	@Override
	public List<ProductAttr> selectFeatureByPage(int begin, int pageSize) {
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("begin", begin);
		map.put("size", pageSize);
		return getSqlSession().selectList(ns+"selectByPage", map);
	}

	@Override
	public List<ProductAttr> selectByAttrNameForCs(String attrName, Integer csid) {
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("attrName", attrName);
		map.put("csid", csid);
		return getSqlSession().selectList(ns+"selectByAttrNameforCs", map);
	}

	@Override
	public void insertAttr(ProductAttr attr) {
		getSqlSession().insert(ns+"insert", attr);
	}

	@Override
	public ProductAttr selectById(Integer featid) {
		
		return getSqlSession().selectOne(ns+"selectByPrimaryKey", featid);
	}

	@Override
	public void updateAttr(ProductAttr attr) {
		getSqlSession().update(ns+"updateByPrimaryKey", attr);
	}

	@Override
	public void deleteById(Integer featId) {
		getSqlSession().delete(ns+"deleteByPrimaryKey", featId);
	}

	@Override
	public int selectCountByCaid(Integer cateId) {
		
		return getSqlSession().selectOne(ns+"selectCountByCsid", cateId);
	}

	@Override
	public List<ProductAttr> selectByPageAndCsid(int begin, int pageSize, Integer cateId) {
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("begin", begin);
		map.put("size", pageSize);
		map.put("csid", cateId);
		return getSqlSession().selectList(ns+"selectByPageAndCsid", map);
	}

	@Override
	public List<ProductAttr> selectSpecOrNot(Integer isSpec,Integer cateId) {
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("isSpec", isSpec);
		map.put("cateId", cateId);
		return getSqlSession().selectList(ns+"selectSpecOrNot", map);
	}

	@Override
	public void saveAttrValue(List<ProductAttrValue> paraList, Integer id) {
		for (ProductAttrValue attrValue : paraList) {
			attrValue.setProductId(id);
			getSqlSession().insert(nsv+"insert", attrValue);
		}
	}

	@Override
	public void deleteByPid(Integer pId) {
		getSqlSession().delete(nsv+"deleteByPid", pId);
	}

	@Override
	public List<ProductAttr> selectAttrByCsid(Integer cid) {
		
		return getSqlSession().selectList(ns+"selectAttrByCsid", cid);
	}

	
}
