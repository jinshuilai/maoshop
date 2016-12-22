package com.mao.shop.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mao.shop.dao.CategoryDao;
import com.mao.shop.po.Category;
import com.mao.shop.po.CategorySecond;

@Repository
public class CategoryDaoImpl extends SqlSessionDaoSupport implements CategoryDao {

	@Autowired
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		super.setSqlSessionFactory(sqlSessionFactory);
	};
	
	private String ns = "com.mao.shop.mapper.CategoryMapper.";
	private String nscs = "com.mao.shop.mapper.CategorySecondMapper.";
	
	@Override
	public List<Category> selectCategorys() {
		
		return getSqlSession().selectList(ns+"selectAll");
	}

	@Override
	public void insert(Category category) {
		getSqlSession().insert(ns+"insert", category);
	}

	@Override
	public void deleteSecondByPid(Integer id) {
		getSqlSession().delete(nscs+"deleteByPid", id);
	}

	@Override
	public void deleteFirst(Integer id) {
		getSqlSession().delete(ns+"deleteByPrimaryKey", id);
	}

	@Override
	public void update(Category category) {
		getSqlSession().update(ns+"updateByPrimaryKey", category);
	}

	@Override
	public List<CategorySecond> selectCateSecond() {
		
		return getSqlSession().selectList(nscs+"selectSecondAll");
	}

	@Override
	public void insertCateSecond(CategorySecond categorySecond) {
		getSqlSession().insert(nscs+"insert", categorySecond);
	}

	@Override
	public void deleteSecond(Integer id) {
		getSqlSession().delete(nscs+"deleteByPrimaryKey", id);
	}

	@Override
	public CategorySecond selectCateSecondById(Integer csid) {
		
		return getSqlSession().selectOne(nscs+"selectByPrimaryKey", csid);
	}

	@Override
	public void updateCateSecond(CategorySecond categorySecond) {
		getSqlSession().update(nscs+"updateByPrimaryKey", categorySecond);
	}

	@Override
	public List<CategorySecond> selectSecondByParentId(Integer parentId) {
		
		return getSqlSession().selectList(nscs+"selectByParentId", parentId);
	}

	@Override
	public List<Category> selectAllWithSecond() {
		
		return getSqlSession().selectList(ns+"selectWithSecond");
	}
}
