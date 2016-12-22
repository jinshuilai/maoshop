package com.mao.shop.dao;

import java.util.List;

import com.mao.shop.po.FeedbackLever;
import com.mao.shop.po.ProductFeedback;

public interface FeedbackDao {

	/**
	 * 保存评价
	 * @param pf
	 */
	void insert(ProductFeedback pf);

	/**
	 * 根据商品id查询评价数量
	 * @param pid
	 * @param level 
	 * @return
	 */
	int selectCount(Integer pid, Integer level);

	/**
	 * 分页查询评价
	 * @param begin
	 * @param i
	 * @param pid
	 * @param level 
	 * @return
	 */
	List<ProductFeedback> selectByPage(int begin, int i, Integer pid, Integer level);

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
	 * 查询评价
	 * @param id
	 * @param cid
	 * @return
	 */
	ProductFeedback selectByItemId(Integer id, Integer cid);

}
