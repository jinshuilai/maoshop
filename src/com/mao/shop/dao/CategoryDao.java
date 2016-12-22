package com.mao.shop.dao;

import java.util.List;

import com.mao.shop.po.Category;
import com.mao.shop.po.CategorySecond;

public interface CategoryDao {

	/**
	 * 查询所有的一级分类
	 * @return 一级分类的list集合
	 */
	public List<Category> selectCategorys();

	/**
	 * 插入一条一级分类数据
	 * @param category 一级分类，name不能为空
	 */
	public void insert(Category category);

	/**
	 * 根据一级分类id删除下属的所有二级分类
	 * @param id 一级分类id
	 */
	public void deleteSecondByPid(Integer id);

	/**
	 * 删除一级分类
	 * @param id 一级分类id
	 */
	public void deleteFirst(Integer id);

	/**
	 * 更新一级分类
	 * @param category
	 */
	public void update(Category category);

	/**
	 * 查询所有的二级分类
	 * @return 二级分类list集合
	 */
	public List<CategorySecond> selectCateSecond();

	/**
	 * 插入一条二级分类数据
	 * @param categorySecond 二级分类
	 */
	public void insertCateSecond(CategorySecond categorySecond);
	
	/**
	 * 根据二级分类id删除二级分类
	 * @param id
	 */
	public void deleteSecond(Integer id);

	/**
	 * 根据二级分类id查询二级分类
	 * @param csid 二级分类id
	 * @return 
	 */
	public CategorySecond selectCateSecondById(Integer csid);

	/**
	 * 更新二级分类数据
	 * @param categorySecond
	 */
	public void updateCateSecond(CategorySecond categorySecond);

	/**
	 * 根据一级分类id查询二级分类
	 * @param parentId
	 * @return
	 */
	public List<CategorySecond> selectSecondByParentId(Integer parentId);

	/**
	 * 查询一级分类包含二级分类
	 * @return
	 */
	public List<Category> selectAllWithSecond();
}
