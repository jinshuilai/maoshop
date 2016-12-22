package com.mao.shop.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mao.shop.dao.SkuDao;
import com.mao.shop.po.ProductSku;
import com.mao.shop.po.ProductSpec;

@Repository
public class SkuDaoImpl extends SqlSessionDaoSupport implements SkuDao {

	@Autowired
	@Override
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		super.setSqlSessionFactory(sqlSessionFactory);
	}
	
	String ns = "com.mao.shop.mapper.ProductSkuMapper.";
	String nss = "com.mao.shop.mapper.ProductSpecMapper.";

	@Override
	public void saveSku(List<ProductSku> skuList, Integer id) {
		for (ProductSku sku : skuList) {
			sku.setProductId(id);
			getSqlSession().insert(ns+"insert", sku);
			
			List<ProductSpec> specList = sku.getSpecList();
			for (ProductSpec spec : specList) {
				spec.setSkuid(sku.getSkuId());
				getSqlSession().insert(nss+"insert", spec);
			}
		}
	}

	@Override
	public void deleteByPid(Integer pId) {
		SqlSession session = this.getSqlSession();
		List<ProductSku> skuList = session.selectList(ns+"selectByPid", pId);
		for (ProductSku sku : skuList) {
			session.delete(nss+"deleteBySkuId", sku.getSkuId());
		}
		session.delete(ns+"deleteByPid", pId);
	}

	@Override
	public ProductSku selectById(Integer skuId) {
		return getSqlSession().selectOne(ns+"selectByPrimaryKey", skuId);
	}

	@Override
	public ProductSku getSkuDetail(Integer skuId) {
		return getSqlSession().selectOne(ns+"selectSkuDetail", skuId);
	}

	@Override
	public int updateStock(Integer skuId, Integer quantity) {
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("skuId", skuId);
		map.put("quantity", quantity);
		return getSqlSession().update(ns+"updateStock", map);
	}
}
