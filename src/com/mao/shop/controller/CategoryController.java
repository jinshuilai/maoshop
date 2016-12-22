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
	
	//һ��������Ϣҳ
	@RequestMapping("/show.do")
	public String show(Model model){
		List<Category> cateList = categoryService.selectAll();
		model.addAttribute("cateList",cateList);
		return "console/category/category";
	}
	
	//���һ������
	@RequestMapping("/addCategory.do")
	public void addCategory(Category category,PrintWriter out) {
		categoryService.save(category);
		out.print("success");
	}
	
	//ɾ��һ������
	@RequestMapping("/delCategory.do")
	public void delCategory(Integer id,PrintWriter out) {
		categoryService.delete(id);
		out.print("success");
	}
	
	//�޸�һ������
	@RequestMapping("/modifyCategory.do")
	public void modifyCategory(Category category,PrintWriter out) {
		categoryService.modify(category);
		out.print("success");
	}
	
	//����������Ϣҳ
	@RequestMapping("/secondShow.do")
	public String secondShow(Model model) {
		List<CategorySecond> csList = categoryService.selectSecond();
		model.addAttribute("csList", csList);
		return "console/category/second";
	}
	
	//��Ӷ���������Ϣҳ
	@RequestMapping("/toAddcs.do")
	public String toAddcs(Model model) {
		List<Category> cateList = categoryService.selectAll();
		model.addAttribute("cateList",cateList);
		return "console/category/addUI";
	}
	
	//��Ӷ�������
	@RequestMapping("/addCs.do")
	public String addCs(CategorySecond categorySecond) {
		categoryService.saveCateSecond(categorySecond);
		return "redirect:secondShow.do";
	}
	
	//ɾ����������
	@RequestMapping("/delCs.do")
	public String delCs(Integer csid) {
		categoryService.deleteCateSecond(csid);
		return "redirect:secondShow.do";
	}
	
	//���������޸�ҳ
	@RequestMapping("/toEditcs.do")
	public String toEditcs(Integer csid,Model model) {
		List<Category> cateList = categoryService.selectAll();
		model.addAttribute("cateList",cateList);
		CategorySecond cSecond = categoryService.selectSecondById(csid);
		model.addAttribute("cateSecond", cSecond);
		return "console/category/addUI";
	}
	
	//�޸Ķ�������
	@RequestMapping("/modifyCs.do")
	public String modifyCs(CategorySecond categorySecond) {
		categoryService.modifyCateSecond(categorySecond);
		return "redirect:secondShow.do";
	}
	
	//ajax��ѯ�������෵��json
	@RequestMapping("/loadOption.do")
	public void loadOption(Integer parentId,HttpServletResponse response) {
		List<CategorySecond> csList = categoryService.selectSecondByParentId(parentId);
		JSONObject jo = new JSONObject();
		jo.accumulate("csList", csList);
		String result = jo.toString();
		MaoUtils.printJSON(result, response);
	}

}
