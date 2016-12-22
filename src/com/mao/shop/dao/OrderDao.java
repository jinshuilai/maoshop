package com.mao.shop.dao;

import java.util.List;
import java.util.Map;

import com.mao.shop.po.Orders;

public interface OrderDao {

	/**
	 * ���涩����Ϣ
	 * @param orders
	 */
	void insert(Orders orders);

	/**
	 * ���ݶ����Ų�ѯ����
	 * @param sn
	 * @param userId 
	 * @return
	 */
	Orders selectBySN(String sn, Integer userId);

	/**
	 * ��ѯ��������������
	 * @param oid
	 * @return
	 */
	Orders selectDetailById(Integer oid);

	/**
	 * ��ѯ��������
	 * @param cid
	 * @param s ����״̬
	 * @return
	 */
	int selectCount(Integer cid, Integer s);

	/**
	 * ��ѯ��������
	 * @param begin
	 * @param pageSize
	 * @param cid
	 * @param s ����״̬
	 * @return
	 */
	List<Orders> selectByPage(int begin, int pageSize, Integer cid, Integer s);

	/**
	 * ���¶���״̬
	 * @param map
	 */
	void updateStatus(Map<String, Object> map);

	/**
	 * ��ѯ���ж�������
	 * @param type 
	 * @return
	 */
	int selectAllCount(Integer type);

	/**
	 * �������ͷ�ҳ��ѯ����
	 * @param begin
	 * @param pageSize
	 * @param type
	 * @return
	 */
	List<Orders> selectAllByPage(int begin, int pageSize, Integer type);

	/**
	 * ����״̬��ѯ��������
	 * @param status
	 * @return
	 */
	int selectCountByStatus(Short status);

}
