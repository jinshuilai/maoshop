package com.mao.shop.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mao.shop.dao.BrandDao;
import com.mao.shop.po.ProductBrand;

@Repository
public class BrandDaoImpl extends SqlSessionDaoSupport implements BrandDao {

	@Autowired
	@Override
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		super.setSqlSessionFactory(sqlSessionFactory);
	}
	
	String ns = "com.mao.shop.mapper.ProductBrandMapper.";

	@Override
	public int selectBrandCount() {
		
		return getSqlSession().selectOne(ns+"selectCount");
	}

	@Override
	public List<ProductBrand> selectBrandByPage(int begin, int pageSize) {
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("begin", begin);
		map.put("size", pageSize);
		return getSqlSession().selectList(ns+"selectByPage",map);
	}

	@Override
	public void delete(Integer bid) {
		getSqlSession().delete(ns+"deleteByPrimaryKey", bid);
	}

	@Override
	public List<ProductBrand> selectBrandByName(String brandName) {
		
		return getSqlSession().selectList(ns+"selectByName", brandName);
	}

	@Override
	public void insertBrand(ProductBrand brand) {
		getSqlSession().insert(ns+"insert", brand);
	}

	@Override
	public ProductBrand selectBrandById(Integer bid) {
		
		return getSqlSession().selectOne(ns+"selectByPrimaryKey", bid);
	}

	@Override
	public void update(ProductBrand brand) {
		getSqlSession().update(ns+"updateByPrimaryKeyWithBLOBs",brand);
	}

	@Override
	public List<String> selectSortList() {
		
		return getSqlSession().selectList(ns+"selectSortList");
	}

	@Override
	public List<ProductBrand> selectBrandBySortChar(String sortChar) {
		
		return getSqlSession().selectList(ns+"selectBySortChar", sortChar);
	}

	@Override
	public List<ProductBrand> selectByCsid(Integer cid) {
		
		return getSqlSession().selectList(ns+"selectByCsid", cid);
	}
}
