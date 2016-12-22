package com.mao.shop.dao;

import java.util.List;

import com.mao.shop.po.Region;

public interface RegionDao {

	/**
	 * 查询所有省份
	 * @return
	 */
	List<Region> selectProvince();

	/**
	 * 根据父id查询子地区
	 * @param parentId
	 * @return
	 */
	List<Region> selectByParentId(Short parentId);

}
