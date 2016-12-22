package com.mao.shop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mao.shop.dao.BrandDao;
import com.mao.shop.po.ProductBrand;
import com.mao.shop.service.BrandService;
import com.mao.shop.utils.PageBean;

@Service
public class BrandServiceImpl implements BrandService {

	@Autowired
	private BrandDao brandDao;

	@Override
	public PageBean selectBrandByPage(Integer pageNum) {
		int count = 0;
		count = brandDao.selectBrandCount();
		
		PageBean pageBean = new PageBean();
		pageBean.setPageNum(pageNum);
		pageBean.setTotalCount(count);
		
		List<ProductBrand> brands = brandDao.selectBrandByPage(pageBean.getBegin(),pageBean.getPageSize());
		pageBean.setDataList(brands);
		
		return pageBean;
	}

	@Override
	public void delete(Integer bid) {
		brandDao.delete(bid);
	}

	@Override
	public List<ProductBrand> selectByName(String brandName) {
		
		return brandDao.selectBrandByName(brandName);
	}

	@Override
	public void save(ProductBrand brand) {
		brandDao.insertBrand(brand);
	}

	@Override
	public ProductBrand selectById(Integer bid) {
		
		return brandDao.selectBrandById(bid);
	}

	@Override
	public void modify(ProductBrand brand) {
		brandDao.update(brand);
	}

	@Override
	public List<String> selectSortList() {
		
		return brandDao.selectSortList();
	}

	@Override
	public List<ProductBrand> selectBySortChar(String sortChar) {
		
		return brandDao.selectBrandBySortChar(sortChar);
	}

	@Override
	public List<ProductBrand> selectByCsid(Integer cid) {
		
		return brandDao.selectByCsid(cid);
	}
}
