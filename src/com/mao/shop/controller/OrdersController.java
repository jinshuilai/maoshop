package com.mao.shop.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mao.shop.po.OrderAddr;
import com.mao.shop.po.Orders;
import com.mao.shop.service.OrderService;
import com.mao.shop.utils.MaoUtils;
import com.mao.shop.utils.PageBean;

@Controller
@RequestMapping("/orders")
public class OrdersController {

	@Autowired
	private OrderService orderService;
	
	@RequestMapping("/show.do")
	public String orders(Integer page,Integer type,Model model) {
		if (page == null) {
			page = 1;
		}
		String num = MaoUtils.readProp("console_pageSize");
		PageBean pageBean = orderService.selectAllByPage(page,new Integer(num),type);
		model.addAttribute("pageBean", pageBean);
		model.addAttribute("show", type);
		return "console/orders/list";
	}
	
	@RequestMapping("/toDeliver.do")
	public String toDeliver(Integer id,Model model) {
		Orders orders = orderService.selectItemByOrderId(id);
		model.addAttribute("order", orders);
		OrderAddr addr = orderService.selectShipByOrderId(id);
		model.addAttribute("addr", addr);
		return "console/orders/check";
	}
	
	@RequestMapping("/deliver.do")
	public String deliver(String sn,Integer uid) {
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("status", Orders.PAY_SEND);
		map.put("userId", uid);
		map.put("sn", sn);
		map.put("sendtime", new Date());
		orderService.updateStatus(map);
		return "redirect:show.do?type=1";
	}
}
