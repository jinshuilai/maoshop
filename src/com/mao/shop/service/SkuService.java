package com.mao.shop.service;

import com.mao.shop.po.ProductSku;

public interface SkuService {

	/**
	 * 查询最小销售单元
	 * @param skuId
	 * @return
	 */
	ProductSku selectById(Integer skuId);

}
