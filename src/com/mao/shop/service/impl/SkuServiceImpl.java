package com.mao.shop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mao.shop.dao.SkuDao;
import com.mao.shop.po.ProductSku;
import com.mao.shop.service.SkuService;

@Service
public class SkuServiceImpl implements SkuService {

	@Autowired
	private SkuDao skuDao;

	@Override
	public ProductSku selectById(Integer skuId) {
		return skuDao.selectById(skuId);
	}
}
