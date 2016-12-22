package com.mao.shop.dao;

import java.util.List;

import com.mao.shop.po.FeedbackLever;
import com.mao.shop.po.ProductFeedback;

public interface FeedbackDao {

	/**
	 * ��������
	 * @param pf
	 */
	void insert(ProductFeedback pf);

	/**
	 * ������Ʒid��ѯ��������
	 * @param pid
	 * @param level 
	 * @return
	 */
	int selectCount(Integer pid, Integer level);

	/**
	 * ��ҳ��ѯ����
	 * @param begin
	 * @param i
	 * @param pid
	 * @param level 
	 * @return
	 */
	List<ProductFeedback> selectByPage(int begin, int i, Integer pid, Integer level);

	/**
	 * ��ѯ��������
	 * @param pid
	 * @return
	 */
	FeedbackLever selectLeverNum(Integer pid);

	/**
	 * ��ѯ��������
	 * @return
	 */
	List<ProductFeedback> selectNewFeedback();

	/**
	 * ��ѯ����
	 * @param id
	 * @param cid
	 * @return
	 */
	ProductFeedback selectByItemId(Integer id, Integer cid);

}
