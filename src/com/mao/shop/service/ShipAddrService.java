package com.mao.shop.service;

import java.util.List;

import com.mao.shop.po.CustShipaddr;
import com.mao.shop.po.Region;

public interface ShipAddrService {

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
	List<Region> selectRegionByParentId(Short parentId);

	/**
	 * ��ѯ�û��ջ���ַ
	 * @param cid
	 * @return
	 */
	List<CustShipaddr> selectByUserId(Integer cid);

	/**
	 * �����ջ���ַ
	 * @param shipaddr
	 */
	void saveShipAddr(CustShipaddr shipaddr);

	/**
	 * ��ѯ�ջ���ַ
	 * @param id
	 * @return
	 */
	CustShipaddr selectByShipId(Integer id);

	/**
	 * �޸��û��ջ���ַ��Ϣ
	 * @param shipaddr
	 */
	void updateShipAddr(CustShipaddr shipaddr);

	/**
	 * �޸�Ĭ�ϵ�ַ
	 * @param shipid
	 * @param userId
	 */
	void updateShipAddrDefault(Integer shipid,Integer userId);

	/**
	 * ɾ���ջ���ַ
	 * @param id
	 */
	void deleteByShipid(Integer id);

}
