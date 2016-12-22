package com.mao.shop.dao;

import java.util.List;

import com.mao.shop.po.ProductSku;

public interface SkuDao {

	/**
	 * 保存商品的销售规格
	 * @param skuList
	 * @param id
	 */
	void saveSku(List<ProductSku> skuList, Integer id);

	/**
	 * 根据商品id删除商品规格属性
	 * @param pId
	 */
	void deleteByPid(Integer pId);

	/**
	 * 查询最小销售单元
	 * @param skuId
	 * @return
	 */
	ProductSku selectById(Integer skuId);

	/**
	 * 查询最小销售单元信息，包含公有的商品信息
	 * @param skuId
	 * @return
	 */
	ProductSku getSkuDetail(Integer skuId);

	/**
	 * 更新库存
	 * @param skuId
	 * @param quantity
	 * @return
	 */
	int updateStock(Integer skuId, Integer quantity);

}
