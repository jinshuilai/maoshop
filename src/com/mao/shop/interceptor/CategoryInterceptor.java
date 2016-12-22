package com.mao.shop.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.mao.shop.po.Category;
import com.mao.shop.service.CategoryService;

public class CategoryInterceptor implements HandlerInterceptor {

	@Autowired
	private CategoryService categoryService;
	
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse arg1, Object arg2, ModelAndView modelAndView)
			throws Exception {
		List<Category> categories = (List<Category>) request.getSession().getAttribute("category");
		if (categories == null) {
			categories = categoryService.selectAllWithSecond();
			request.getSession().setAttribute("category", categories);
		}
		if (modelAndView != null) {
			modelAndView.addObject("category", categories);
			System.out.println("===========装载分类，"+ categories.size() +"个==============");
		}

	}

	@Override
	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2) throws Exception {
		// TODO Auto-generated method stub
		return true;
	}

}
