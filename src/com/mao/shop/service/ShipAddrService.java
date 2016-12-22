package com.mao.shop.service;

import java.util.List;

import com.mao.shop.po.CustShipaddr;
import com.mao.shop.po.Region;

public interface ShipAddrService {

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
	List<Region> selectRegionByParentId(Short parentId);

	/**
	 * 查询用户收货地址
	 * @param cid
	 * @return
	 */
	List<CustShipaddr> selectByUserId(Integer cid);

	/**
	 * 保存收货地址
	 * @param shipaddr
	 */
	void saveShipAddr(CustShipaddr shipaddr);

	/**
	 * 查询收货地址
	 * @param id
	 * @return
	 */
	CustShipaddr selectByShipId(Integer id);

	/**
	 * 修改用户收货地址信息
	 * @param shipaddr
	 */
	void updateShipAddr(CustShipaddr shipaddr);

	/**
	 * 修改默认地址
	 * @param shipid
	 * @param userId
	 */
	void updateShipAddrDefault(Integer shipid,Integer userId);

	/**
	 * 删除收货地址
	 * @param id
	 */
	void deleteByShipid(Integer id);

}
