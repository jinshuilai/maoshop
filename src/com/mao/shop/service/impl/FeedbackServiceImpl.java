package com.mao.shop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mao.shop.dao.FeedbackDao;
import com.mao.shop.po.FeedbackLever;
import com.mao.shop.po.ProductFeedback;
import com.mao.shop.service.FeedbackService;
import com.mao.shop.utils.PageBean;

@Service
public class FeedbackServiceImpl implements FeedbackService {

	@Autowired
	private FeedbackDao feedbackDao;

	@Override
	public void saveFeedBack(List<ProductFeedback> fbList) {
		for (ProductFeedback pf : fbList) {
			feedbackDao.insert(pf);
		}	
	}

	@Override
	public PageBean selectByPage(Integer page, int pageSize, Integer pid,Integer level) {
		int count = feedbackDao.selectCount(pid,level);
		PageBean pageBean = new PageBean();
		pageBean.setPageNum(page);
		pageBean.setPageSize(pageSize);
		pageBean.setTotalCount(count);
		List<ProductFeedback> list = feedbackDao.selectByPage(pageBean.getBegin(),pageSize,pid,level);
		for (ProductFeedback pfb : list) {
			if (pfb.getAnonymous() == 1) {
				String str = pfb.getCustName();
				StringBuffer sb = new StringBuffer(str);
				for (int i = 1; i < str.length()-1; i++) {
					sb.setCharAt(i, '*');
				}
				pfb.setCustName(sb.toString());
			}
		}
		pageBean.setDataList(list);
		return pageBean;
	}

	@Override
	public FeedbackLever selectLeverNum(Integer pid) {
		FeedbackLever lever = feedbackDao.selectLeverNum(pid);
		
		return lever;
	}

	@Override
	public List<ProductFeedback> selectNewFeedback() {
		
		return feedbackDao.selectNewFeedback();
	}

	@Override
	public ProductFeedback selectByItemId(Integer id, Integer cid) {
		
		return feedbackDao.selectByItemId(id,cid);
	}
}
