package com.mao.shop.service;

import java.util.List;

import com.mao.shop.po.FeedbackLever;
import com.mao.shop.po.ProductFeedback;
import com.mao.shop.utils.PageBean;

public interface FeedbackService {

	/**
	 * ��������
	 * @param fbList
	 */
	void saveFeedBack(List<ProductFeedback> fbList);

	/**
	 * ��ҳ��ѯ����
	 * @param page
	 * @param pageSize
	 * @param pid
	 * @param level 
	 * @return
	 */
	PageBean selectByPage(Integer page, int pageSize, Integer pid, Integer level);

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
	 * ���ݶ�����id��ѯ����
	 * @param id
	 * @param cid 
	 * @return
	 */
	ProductFeedback selectByItemId(Integer id, Integer cid);

}
