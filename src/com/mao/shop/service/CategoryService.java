package com.mao.shop.service;

import java.util.List;

import com.mao.shop.po.Category;
import com.mao.shop.po.CategorySecond;

public interface CategoryService {

	/**
	 * ��ѯ���е�һ������
	 * @return һ�������list����
	 */
	public List<Category> selectAll();

	/**
	 * ����һ��һ����������
	 * @param category һ�����࣬name����Ϊ��
	 */
	public void save(Category category);

	/**
	 * ɾ��һ�����࣬ͬʱ�ἶ��ɾ����������
	 * @param id һ������id
	 */
	public void delete(Integer id);

	/**
	 * �޸�һ������
	 * @param category
	 */
	public void modify(Category category);

	/**
	 * ��ѯ���еĶ�������
	 * @return
	 */
	public List<CategorySecond> selectSecond();

	/**
	 * ����һ��������������
	 * @param categorySecond
	 */
	public void saveCateSecond(CategorySecond categorySecond);

	/**
	 * ���ݶ�������idɾ����������
	 * @param csid
	 */
	public void deleteCateSecond(Integer csid);

	/**
	 * ���ݶ�������id��ѯ��������
	 * @param csid
	 * @return
	 */
	public CategorySecond selectSecondById(Integer csid);

	/**
	 * �޸Ķ�������
	 * @param categorySecond
	 */
	public void modifyCateSecond(CategorySecond categorySecond);

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
