package com.mao.shop.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mao.shop.dao.ImagesDao;
import com.mao.shop.po.ProductImage;

@Repository
public class ImagesDaoImpl extends SqlSessionDaoSupport implements ImagesDao {

	@Autowired
	@Override
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		super.setSqlSessionFactory(sqlSessionFactory);
	}
	
	String ns = "com.mao.shop.mapper.ProductImageMapper.";

	@Override
	public void saveImgs(List<ProductImage> imgList, Integer id) {
		for (ProductImage image : imgList) {
			image.setProductId(id);
			getSqlSession().insert(ns+"insert",image);
		}
	}

	@Override
	public void deleteByPid(Integer pId) {
		getSqlSession().delete(ns+"deleteByPid", pId);
	}
}
