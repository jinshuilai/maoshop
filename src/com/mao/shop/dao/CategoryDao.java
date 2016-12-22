package com.mao.shop.dao;

import java.util.List;

import com.mao.shop.po.Category;
import com.mao.shop.po.CategorySecond;

public interface CategoryDao {

	/**
	 * ��ѯ���е�һ������
	 * @return һ�������list����
	 */
	public List<Category> selectCategorys();

	/**
	 * ����һ��һ����������
	 * @param category һ�����࣬name����Ϊ��
	 */
	public void insert(Category category);

	/**
	 * ����һ������idɾ�����������ж�������
	 * @param id һ������id
	 */
	public void deleteSecondByPid(Integer id);

	/**
	 * ɾ��һ������
	 * @param id һ������id
	 */
	public void deleteFirst(Integer id);

	/**
	 * ����һ������
	 * @param category
	 */
	public void update(Category category);

	/**
	 * ��ѯ���еĶ�������
	 * @return ��������list����
	 */
	public List<CategorySecond> selectCateSecond();

	/**
	 * ����һ��������������
	 * @param categorySecond ��������
	 */
	public void insertCateSecond(CategorySecond categorySecond);
	
	/**
	 * ���ݶ�������idɾ����������
	 * @param id
	 */
	public void deleteSecond(Integer id);

	/**
	 * ���ݶ�������id��ѯ��������
	 * @param csid ��������id
	 * @return 
	 */
	public CategorySecond selectCateSecondById(Integer csid);

	/**
	 * ���¶�����������
	 * @param categorySecond
	 */
	public void updateCateSecond(CategorySecond categorySecond);

	/**
	 * ����һ������id��ѯ��������
	 * @param parentId
	 * @return
	 */
	public List<CategorySecond> selectSecondByParentId(Integer parentId);

	/**
	 * ��ѯһ�����������������
	 * @return
	 */
	public List<Category> selectAllWithSecond();
}
