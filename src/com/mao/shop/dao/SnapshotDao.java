package com.mao.shop.dao;

import com.mao.shop.po.ProductSnapshot;

public interface SnapshotDao {

	/**
	 * 保存交易快照
	 * @param snapshot
	 */
	void save(ProductSnapshot snapshot);

	/**
	 * 查询交易快照
	 * @param id
	 * @return
	 */
	ProductSnapshot select(Integer id);

}
