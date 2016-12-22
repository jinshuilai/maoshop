package com.mao.shop.dao;

import java.util.List;

import com.mao.shop.po.ProductSku;

public interface SkuDao {

	/**
	 * ������Ʒ�����۹��
	 * @param skuList
	 * @param id
	 */
	void saveSku(List<ProductSku> skuList, Integer id);

	/**
	 * ������Ʒidɾ����Ʒ�������
	 * @param pId
	 */
	void deleteByPid(Integer pId);

	/**
	 * ��ѯ��С���۵�Ԫ
	 * @param skuId
	 * @return
	 */
	ProductSku selectById(Integer skuId);

	/**
	 * ��ѯ��С���۵�Ԫ��Ϣ���������е���Ʒ��Ϣ
	 * @param skuId
	 * @return
	 */
	ProductSku getSkuDetail(Integer skuId);

	/**
	 * ���¿��
	 * @param skuId
	 * @param quantity
	 * @return
	 */
	int updateStock(Integer skuId, Integer quantity);

}
