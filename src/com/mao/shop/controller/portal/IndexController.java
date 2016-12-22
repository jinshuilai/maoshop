package com.mao.shop.controller.portal;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mao.shop.po.CategorySecond;
import com.mao.shop.po.Product;
import com.mao.shop.po.ProductFeedback;
import com.mao.shop.service.CategoryService;
import com.mao.shop.service.FeedbackService;
import com.mao.shop.service.ProductService;
import com.mao.shop.utils.MaoUtils;

@Controller
public class IndexController {
	
	@Autowired
	private ProductService productService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private FeedbackService feedbackService;
	
	@RequestMapping("/index")
	public String index(Model model,HttpServletRequest request) {
		//品质生活-最贵
		List<Product> expenList = productService.selectExpensiveProduct();
		model.addAttribute("expenList", expenList);
		//新品推荐-最新
		List<Product> newList = productService.selectNewProduct();
		model.addAttribute("newList", newList);
		//买家分享
		List<ProductFeedback> fbList = feedbackService.selectNewFeedback();
		model.addAttribute("fbList", fbList);
		//猜你喜欢
		String likeKey = MaoUtils.readProp("cookie_likeKey");
		Cookie[] cookies = request.getCookies();
		String cartVal = null;
		if (cookies != null && cookies.length > 0) {
			for (Cookie cookie : cookies) {
				String cookieKey = cookie.getName();
				if (StringUtils.equals(likeKey, cookieKey)) {
					cartVal = cookie.getValue();
				}
			}
		}
		List<Product> likeList = new ArrayList<Product>();
		if (null != cartVal) {
			//数据多不要这样取
			List<Product> list = productService.selectByCsid(new Integer(cartVal));
			if (list.size() < 5) {
				likeList = list;
			}else{
				Integer[] array = MaoUtils.getRandomIntegerArray(list.size(), 4);
				for (Integer integer : array) {
					likeList.add(list.get(integer));
				}
			}
			
		}else{
			likeList = newList.subList(newList.size() - 4, newList.size());
		}
		model.addAttribute("likeList", likeList);
		
		model.addAttribute("onNum", 1);
		return "portal/index";
	}
	
	@RequestMapping("/clothing.do")
	public String clothing(Model model) {
		List<CategorySecond> csList = categoryService.selectSecondByParentId(1);
		model.addAttribute("csList",csList);
		model.addAttribute("onNum", 2);
		return "portal/clothing";
	}
	
	@RequestMapping("/electric.do")
	public String electric(Model model) {
	
		return "portal/electric";
	}
	
	@RequestMapping("/404.do")
	public String nopage() {
		
		return "portal/404";
	}
	
}
