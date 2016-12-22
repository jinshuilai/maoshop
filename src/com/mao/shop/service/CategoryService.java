package com.mao.shop.service;

import java.util.List;

import com.mao.shop.po.Category;
import com.mao.shop.po.CategorySecond;

public interface CategoryService {

	/**
	 * 查询所有的一级分类
	 * @return 一级分类的list集合
	 */
	public List<Category> selectAll();

	/**
	 * 保存一条一级分类数据
	 * @param category 一级分类，name不能为空
	 */
	public void save(Category category);

	/**
	 * 删除一级分类，同时会级联删除二级分类
	 * @param id 一级分类id
	 */
	public void delete(Integer id);

	/**
	 * 修改一级分类
	 * @param category
	 */
	public void modify(Category category);

	/**
	 * 查询所有的二级分类
	 * @return
	 */
	public List<CategorySecond> selectSecond();

	/**
	 * 保存一条二级分类数据
	 * @param categorySecond
	 */
	public void saveCateSecond(CategorySecond categorySecond);

	/**
	 * 根据二级分类id删除二级分类
	 * @param csid
	 */
	public void deleteCateSecond(Integer csid);

	/**
	 * 根据二级分类id查询二级数据
	 * @param csid
	 * @return
	 */
	public CategorySecond selectSecondById(Integer csid);

	/**
	 * 修改二级分类
	 * @param categorySecond
	 */
	public void modifyCateSecond(CategorySecond categorySecond);

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
