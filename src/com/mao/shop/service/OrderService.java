package com.mao.shop.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mao.shop.po.OrderAddr;
import com.mao.shop.po.OrderItem;
import com.mao.shop.po.Orders;
import com.mao.shop.po.ProductSnapshot;
import com.mao.shop.utils.PageBean;

public interface OrderService {

	/**
	 * ���涩����Ϣ
	 * @param orders
	 * @param orderAddr
	 * @param itemList
	 * @param request
	 * @param response
	 */
	void saveOrder(Orders orders, OrderAddr orderAddr, List<OrderItem> itemList, HttpServletRequest request,
			HttpServletResponse response);

	/**
	 * ���ݶ�����Ų�ѯ����
	 * @param sn
	 * @param integer 
	 * @return
	 */
	Orders selectBySN(String sn, Integer integer);

	/**
	 * ���ݶ���id��ѯ�������飬����������
	 * @param oid
	 * @return
	 */
	Orders selectDetailByOid(Integer oid);

	/**
	 * ��ѯ�û���ȫ������
	 * @param cid
	 * @param page 
	 * @param s ����״̬
	 * @return
	 */
	PageBean selectAllByUserId(Integer cid, Integer page, Integer s);

	/**
	 * ���Ķ���״̬
	 * @param sn �������к�
	 * @param status ���Ϊ��״̬ 
	 * @param uid �û�id
	 */
	void updateStatus(Map<String, Object> map);

	/**
	 * ��ѯ���׿���
	 * @param id
	 * @return
	 */
	ProductSnapshot selectSnapshot(Integer id);

	/**
	 * ��ѯ���ж���
	 * @param page
	 * @param integer
	 * @param type
	 * @return
	 */
	PageBean selectAllByPage(Integer page, Integer integer, Integer type);

	/**
	 * ��ѯ������
	 * @param id
	 * @return
	 */
	Orders selectItemByOrderId(Integer id);

	/**
	 * ��ѯ�ջ���ַ
	 * @param id
	 * @return
	 */
	OrderAddr selectShipByOrderId(Integer id);

	/**
	 * ��ѯ�Ѹ���Ķ���
	 * @return
	 */
	int selectNoDealCount();

}
