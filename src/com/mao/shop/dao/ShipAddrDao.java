package com.mao.shop.dao;

import java.util.List;

import com.mao.shop.po.CustShipaddr;

public interface ShipAddrDao {

	/**
	 * ��ѯ�û��ջ���ַ
	 * @param cid
	 * @return
	 */
	List<CustShipaddr> selectByUserId(Integer cid);

	/**
	 * ����һ���ջ���ַ
	 * @param shipaddr
	 */
	void saveShipAddr(CustShipaddr shipaddr);

	/**
	 * ����û���Ĭ�ϵ�ַ
	 * @param customerId
	 */
	void resetDefalut(Integer customerId);

	/**
	 * ��ѯһ���ջ���ַ
	 * @param id
	 * @return
	 */
	CustShipaddr selectByShipId(Integer id);

	/**
	 * �޸��ջ���ַ
	 * @param shipaddr
	 */
	void updateShipAddr(CustShipaddr shipaddr);

	/**
	 * �޸�Ĭ�ϵ�ַ
	 * @param id
	 */
	void updateDefault(Integer id);

	/**
	 * ɾ���ջ���ַ
	 * @param id
	 */
	void deleteByShipId(Integer id);

}
