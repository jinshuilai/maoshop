package com.mao.shop.service;

import com.mao.shop.po.ProductSku;

public interface SkuService {

	/**
	 * ��ѯ��С���۵�Ԫ
	 * @param skuId
	 * @return
	 */
	ProductSku selectById(Integer skuId);

}
