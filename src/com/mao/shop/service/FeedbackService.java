package com.mao.shop.service;

import java.util.List;

import com.mao.shop.po.FeedbackLever;
import com.mao.shop.po.ProductFeedback;
import com.mao.shop.utils.PageBean;

public interface FeedbackService {

	/**
	 * 保存评价
	 * @param fbList
	 */
	void saveFeedBack(List<ProductFeedback> fbList);

	/**
	 * 分页查询评价
	 * @param page
	 * @param pageSize
	 * @param pid
	 * @param level 
	 * @return
	 */
	PageBean selectByPage(Integer page, int pageSize, Integer pid, Integer level);

	/**
	 * 查询评价数量
	 * @param pid
	 * @return
	 */
	FeedbackLever selectLeverNum(Integer pid);

	/**
	 * 查询最新评价
	 * @return
	 */
	List<ProductFeedback> selectNewFeedback();

	/**
	 * 根据订单项id查询评价
	 * @param id
	 * @param cid 
	 * @return
	 */
	ProductFeedback selectByItemId(Integer id, Integer cid);

}
