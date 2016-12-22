package com.mao.shop.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mao.shop.service.OrderService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private OrderService orderService;
	
	@RequestMapping("/toIndex.do")
	public String toAdmin(HttpSession session,Model model) {
		session.setAttribute("username", "username");
		int num = orderService.selectNoDealCount();
		model.addAttribute("message", "有"+num+"个未处理的订单");
		return "console/index";
	}
}
