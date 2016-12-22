package com.mao.shop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mao.shop.dao.CategoryDao;
import com.mao.shop.po.Category;
import com.mao.shop.po.CategorySecond;
import com.mao.shop.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryDao categoryDao;
	
	@Override
	public List<Category> selectAll() {
		
		return categoryDao.selectCategorys();
	}

	@Override
	public void save(Category category) {
		categoryDao.insert(category);
	}

	@Override
	public void delete(Integer id) {
		categoryDao.deleteSecondByPid(id);
		categoryDao.deleteFirst(id);
	}

	@Override
	public void modify(Category category) {
		categoryDao.update(category);
	}

	@Override
	public List<CategorySecond> selectSecond() {
	
		return categoryDao.selectCateSecond();
	}

	@Override
	public void saveCateSecond(CategorySecond categorySecond) {
		categoryDao.insertCateSecond(categorySecond);
	}

	@Override
	public void deleteCateSecond(Integer csid) {
		categoryDao.deleteSecond(csid);
	}

	@Override
	public CategorySecond selectSecondById(Integer csid) {
		
		return categoryDao.selectCateSecondById(csid);
	}

	@Override
	public void modifyCateSecond(CategorySecond categorySecond) {
		categoryDao.updateCateSecond(categorySecond);
	}

	@Override
	public List<CategorySecond> selectSecondByParentId(Integer parentId) {
		
		return categoryDao.selectSecondByParentId(parentId);
	}

	@Override
	public List<Category> selectAllWithSecond() {
		
		return categoryDao.selectAllWithSecond();
	}

}
