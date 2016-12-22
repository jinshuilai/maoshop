package com.mao.shop.controller;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mao.shop.po.Category;
import com.mao.shop.po.CategorySecond;
import com.mao.shop.service.CategoryService;
import com.mao.shop.utils.MaoUtils;

@Controller
@RequestMapping("/category")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	//一级分类信息页
	@RequestMapping("/show.do")
	public String show(Model model){
		List<Category> cateList = categoryService.selectAll();
		model.addAttribute("cateList",cateList);
		return "console/category/category";
	}
	
	//添加一级分类
	@RequestMapping("/addCategory.do")
	public void addCategory(Category category,PrintWriter out) {
		categoryService.save(category);
		out.print("success");
	}
	
	//删除一级分类
	@RequestMapping("/delCategory.do")
	public void delCategory(Integer id,PrintWriter out) {
		categoryService.delete(id);
		out.print("success");
	}
	
	//修改一级分类
	@RequestMapping("/modifyCategory.do")
	public void modifyCategory(Category category,PrintWriter out) {
		categoryService.modify(category);
		out.print("success");
	}
	
	//二级分类信息页
	@RequestMapping("/secondShow.do")
	public String secondShow(Model model) {
		List<CategorySecond> csList = categoryService.selectSecond();
		model.addAttribute("csList", csList);
		return "console/category/second";
	}
	
	//添加二级分类信息页
	@RequestMapping("/toAddcs.do")
	public String toAddcs(Model model) {
		List<Category> cateList = categoryService.selectAll();
		model.addAttribute("cateList",cateList);
		return "console/category/addUI";
	}
	
	//添加二级分类
	@RequestMapping("/addCs.do")
	public String addCs(CategorySecond categorySecond) {
		categoryService.saveCateSecond(categorySecond);
		return "redirect:secondShow.do";
	}
	
	//删除二级分类
	@RequestMapping("/delCs.do")
	public String delCs(Integer csid) {
		categoryService.deleteCateSecond(csid);
		return "redirect:secondShow.do";
	}
	
	//二级分类修改页
	@RequestMapping("/toEditcs.do")
	public String toEditcs(Integer csid,Model model) {
		List<Category> cateList = categoryService.selectAll();
		model.addAttribute("cateList",cateList);
		CategorySecond cSecond = categoryService.selectSecondById(csid);
		model.addAttribute("cateSecond", cSecond);
		return "console/category/addUI";
	}
	
	//修改二级分类
	@RequestMapping("/modifyCs.do")
	public String modifyCs(CategorySecond categorySecond) {
		categoryService.modifyCateSecond(categorySecond);
		return "redirect:secondShow.do";
	}
	
	//ajax查询二级分类返回json
	@RequestMapping("/loadOption.do")
	public void loadOption(Integer parentId,HttpServletResponse response) {
		List<CategorySecond> csList = categoryService.selectSecondByParentId(parentId);
		JSONObject jo = new JSONObject();
		jo.accumulate("csList", csList);
		String result = jo.toString();
		MaoUtils.printJSON(result, response);
	}

}
