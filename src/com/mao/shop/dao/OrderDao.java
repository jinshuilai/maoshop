package com.mao.shop.dao;

import java.util.List;
import java.util.Map;

import com.mao.shop.po.Orders;

public interface OrderDao {

	/**
	 * 保存订单信息
	 * @param orders
	 */
	void insert(Orders orders);

	/**
	 * 根据订单号查询订单
	 * @param sn
	 * @param userId 
	 * @return
	 */
	Orders selectBySN(String sn, Integer userId);

	/**
	 * 查询订单包含订单项
	 * @param oid
	 * @return
	 */
	Orders selectDetailById(Integer oid);

	/**
	 * 查询订单总数
	 * @param cid
	 * @param s 订单状态
	 * @return
	 */
	int selectCount(Integer cid, Integer s);

	/**
	 * 查询订单数据
	 * @param begin
	 * @param pageSize
	 * @param cid
	 * @param s 订单状态
	 * @return
	 */
	List<Orders> selectByPage(int begin, int pageSize, Integer cid, Integer s);

	/**
	 * 更新订单状态
	 * @param map
	 */
	void updateStatus(Map<String, Object> map);

	/**
	 * 查询所有订单数量
	 * @param type 
	 * @return
	 */
	int selectAllCount(Integer type);

	/**
	 * 根据类型分页查询订单
	 * @param begin
	 * @param pageSize
	 * @param type
	 * @return
	 */
	List<Orders> selectAllByPage(int begin, int pageSize, Integer type);

	/**
	 * 根据状态查询订单数量
	 * @param status
	 * @return
	 */
	int selectCountByStatus(Short status);

}
