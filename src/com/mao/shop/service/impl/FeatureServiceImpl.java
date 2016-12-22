package com.mao.shop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mao.shop.dao.FeatureDao;
import com.mao.shop.po.ProductAttr;
import com.mao.shop.service.FeatureService;
import com.mao.shop.utils.PageBean;

@Service
public class FeatureServiceImpl implements FeatureService {

	@Autowired
	private FeatureDao featureDao;

	@Override
	public PageBean selectFeatureByPage(Integer pageNum,Integer pageSize) {
		int count = 0;
		count = featureDao.selectFeatureCount();
		
		PageBean pageBean = new PageBean();
		pageBean.setPageNum(pageNum);
		pageBean.setPageSize(pageSize);
		pageBean.setTotalCount(count);
		
		List<ProductAttr> attrs = featureDao.selectFeatureByPage(pageBean.getBegin(),pageBean.getPageSize());
		pageBean.setDataList(attrs);
		
		return pageBean;
	}

	@Override
	public List<ProductAttr> selectByAttrNameforCs(String attrName, Integer csid) {
		
		return featureDao.selectByAttrNameForCs(attrName,csid);
	}

	@Override
	public void saveAttr(ProductAttr attr) {
		featureDao.insertAttr(attr);
	}

	@Override
	public ProductAttr selectById(Integer featid) {
		
		return featureDao.selectById(featid);
	}

	@Override
	public void modify(ProductAttr attr) {
		featureDao.updateAttr(attr);
	}

	@Override
	public void deleteAttr(Integer featId) {
		featureDao.deleteById(featId);
	}

	@Override
	public PageBean selectByPageAndCsid(Integer pageNum, Integer cateId,Integer pageSize) {
		int count = 0;
		count = featureDao.selectCountByCaid(cateId);
		
		PageBean pageBean = new PageBean();
		pageBean.setPageNum(pageNum);
		pageBean.setPageSize(pageSize);
		pageBean.setTotalCount(count);
		
		List<ProductAttr> attrs = featureDao.selectByPageAndCsid(pageBean.getBegin(),pageBean.getPageSize(),cateId);
		pageBean.setDataList(attrs);
		
		return pageBean;
	}

	@Override
	public List<ProductAttr> selectFeatureIsSpec(Integer isSpec,Integer cateId) {
		
		return featureDao.selectSpecOrNot(isSpec,cateId);
	}

	@Override
	public List<ProductAttr> selectAttrByCsid(Integer cid) {
		
		return featureDao.selectAttrByCsid(cid);
	}
}
