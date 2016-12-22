package com.mao.shop.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mao.shop.dao.OrderAddrDao;
import com.mao.shop.dao.OrderDao;
import com.mao.shop.dao.OrderItemDao;
import com.mao.shop.dao.SkuDao;
import com.mao.shop.exception.StockException;
import com.mao.shop.po.OrderAddr;
import com.mao.shop.po.OrderItem;
import com.mao.shop.po.Orders;
import com.mao.shop.po.ProductAttrValue;
import com.mao.shop.po.ProductInfo;
import com.mao.shop.po.ProductSnapshot;
import com.mao.shop.service.CartService;
import com.mao.shop.service.OrderService;
import com.mao.shop.service.ProductService;
import com.mao.shop.utils.PageBean;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderDao orderDao;
	@Autowired
	private OrderAddrDao orderAddrDao;
	@Autowired
	private OrderItemDao orderItemDao;
	@Autowired
	private SkuDao skuDao;
	@Autowired
	private CartService cartService;
	@Autowired
	private ProductService productService;

	@Override
	public void saveOrder(Orders orders, OrderAddr orderAddr, List<OrderItem> itemList, HttpServletRequest request,
			HttpServletResponse response) {
		//设置订单状态
		orders.setStatus(Orders.NO_PAY);
		orders.setIsDelete(Orders.NO_PAY);
		orders.setPlaceAt(new Date());
		//保存订单，返回主键
		orderDao.insert(orders);
		orderAddr.setOrderId(orders.getOid());
		orderAddrDao.insert(orderAddr);
		for (OrderItem item : itemList) {
			//保存快照
			ProductInfo info = productService.selectProductDetail(item.getProductId());
			ProductSnapshot snapshot = new ProductSnapshot();
			snapshot.setProductId(info.getProId());
			snapshot.setProductName(info.getPname());
			snapshot.setSpec(item.getSkuSpec());
			snapshot.setBuyPrice(item.getPrice());
			snapshot.setDesctext(info.getDesctext());
			List<ProductAttrValue> pav = info.getParaList();
			String para = "";
			for (ProductAttrValue av : pav) {
				para += av.getFeatureName() + "：" + av.getAttrValue() + ",";
			}
			snapshot.setParamter(para);
			snapshot.setProductImg(info.getImageList().get(0).getFilepath());
			productService.saveSnapshot(snapshot);
			//保存订单项
			item.setOrderId(orders.getOid());
			item.setSnapId(snapshot.getSnapId());
			orderItemDao.insert(item);
			//更新库存
			int flag = skuDao.updateStock(item.getSkuId(),item.getQuantity());
			if (flag == 0) {
				throw new StockException();
			}
		}
		cartService.clearCart(request, response);
	}

	@Override
	public Orders selectBySN(String sn,Integer userId) {
		
		return orderDao.selectBySN(sn,userId);
	}

	@Override
	public Orders selectDetailByOid(Integer oid) {
		return orderDao.selectDetailById(oid);
	}

	@Override
	public PageBean selectAllByUserId(Integer cid,Integer page,Integer s) {
		int count = orderDao.selectCount(cid,s);
		PageBean pageBean = new PageBean();
		pageBean.setPageNum(page);
		pageBean.setPageSize(5);
		pageBean.setTotalCount(count);
		List<Orders> orderList = orderDao.selectByPage(pageBean.getBegin(),pageBean.getPageSize(),cid,s);
		List<Orders> list = new ArrayList<Orders>();
		for (Orders order : orderList) {
			Orders o = orderDao.selectDetailById(order.getOid());
			list.add(o);
		}
		pageBean.setDataList(list);
		return pageBean;
	}

	@Override
	public void updateStatus(Map<String, Object> map) {
		
		orderDao.updateStatus(map);
	}

	@Override
	public ProductSnapshot selectSnapshot(Integer id) {
		
		return productService.selectSnapshot(id);
	}

	@Override
	public PageBean selectAllByPage(Integer page, Integer integer, Integer type) {
		int count = orderDao.selectAllCount(type);
		PageBean pageBean = new PageBean();
		pageBean.setPageNum(page);
		pageBean.setPageSize(integer);
		pageBean.setTotalCount(count);
		List<Orders> orderList = orderDao.selectAllByPage(pageBean.getBegin(),pageBean.getPageSize(),type);
		
		pageBean.setDataList(orderList);
		return pageBean;
	}

	@Override
	public Orders selectItemByOrderId(Integer id) {
		return orderDao.selectDetailById(id);
	}

	@Override
	public OrderAddr selectShipByOrderId(Integer id) {
		return orderAddrDao.selectByOrderId(id);
	}

	@Override
	public int selectNoDealCount() {
		
		return orderDao.selectCountByStatus(Orders.PAY);
	}
}
