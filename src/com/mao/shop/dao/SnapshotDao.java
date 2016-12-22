package com.mao.shop.dao;

import com.mao.shop.po.ProductSnapshot;

public interface SnapshotDao {

	/**
	 * ���潻�׿���
	 * @param snapshot
	 */
	void save(ProductSnapshot snapshot);

	/**
	 * ��ѯ���׿���
	 * @param id
	 * @return
	 */
	ProductSnapshot select(Integer id);

}
