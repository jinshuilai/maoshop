package com.mao.shop.dao;

import java.util.List;

import com.mao.shop.po.CustShipaddr;

public interface ShipAddrDao {

	/**
	 * 查询用户收货地址
	 * @param cid
	 * @return
	 */
	List<CustShipaddr> selectByUserId(Integer cid);

	/**
	 * 保存一条收货地址
	 * @param shipaddr
	 */
	void saveShipAddr(CustShipaddr shipaddr);

	/**
	 * 清除用户的默认地址
	 * @param customerId
	 */
	void resetDefalut(Integer customerId);

	/**
	 * 查询一条收货地址
	 * @param id
	 * @return
	 */
	CustShipaddr selectByShipId(Integer id);

	/**
	 * 修改收货地址
	 * @param shipaddr
	 */
	void updateShipAddr(CustShipaddr shipaddr);

	/**
	 * 修改默认地址
	 * @param id
	 */
	void updateDefault(Integer id);

	/**
	 * 删除收货地址
	 * @param id
	 */
	void deleteByShipId(Integer id);

}
