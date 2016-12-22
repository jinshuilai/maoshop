package com.mao.shop.dao;

import java.util.List;

import com.mao.shop.po.Region;

public interface RegionDao {

	/**
	 * ��ѯ����ʡ��
	 * @return
	 */
	List<Region> selectProvince();

	/**
	 * ���ݸ�id��ѯ�ӵ���
	 * @param parentId
	 * @return
	 */
	List<Region> selectByParentId(Short parentId);

}
