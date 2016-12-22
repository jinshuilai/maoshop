package com.mao.shop.controller.portal;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mao.shop.po.CategorySecond;
import com.mao.shop.po.FeedbackLever;
import com.mao.shop.po.Product;
import com.mao.shop.po.ProductAttr;
import com.mao.shop.po.ProductBrand;
import com.mao.shop.po.ProductInfo;
import com.mao.shop.po.ProductSku;
import com.mao.shop.po.SearchResult;
import com.mao.shop.service.BrandService;
import com.mao.shop.service.CategoryService;
import com.mao.shop.service.FeatureService;
import com.mao.shop.service.FeedbackService;
import com.mao.shop.service.ProductService;
import com.mao.shop.service.SkuService;
import com.mao.shop.utils.MaoUtils;
import com.mao.shop.utils.PageBean;
import com.mao.shop.utils.ProductQuery;

@Controller
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService productService;
	@Autowired
	private BrandService brandService;
	@Autowired
	private FeatureService featureService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private SkuService skuService;
	@Autowired
	private FeedbackService feedbackService;
	
	//列出符合筛选条件的商品信息
	@RequestMapping("/list.do")
	public String list(ProductQuery query,Integer page,String para,Model model,HttpServletRequest request) throws UnsupportedEncodingException {
		if (page == null) {
			page = 1;
		}
		List<ProductAttr> attrs = featureService.selectAttrByCsid(query.getCid());//全部
		List<ProductAttr> select = new ArrayList<ProductAttr>();//选择过的属性
		if (StringUtils.isNotBlank(para)) {
			para = URLDecoder.decode(para, "utf-8");//解码
			System.out.println("=============="+para);
			List<String> paraList = new ArrayList<String>();//筛选条件
			Map<String, String> paraMap = new HashMap<String, String>();
			for (ProductAttr pa : attrs) {
				paraMap.put(pa.getPaId().toString(), pa.getAttrName());
			}
			String[] paras = para.split(",");
			for (String string : paras) {
				String[] str = string.split(":");
				String name = str[1];
				paraList.add(name);
				String id = str[0];
				ProductAttr attr = new ProductAttr();
				attr.setPaId(new Integer(id));
				attr.setAttrName(paraMap.get(id));
				attr.setOptionValues(name);
				select.add(attr);
				attrs.remove(attr);
			}
			query.setParaList(paraList);
			
		}
		if (query.getPrice() != null) {
			model.addAttribute("price", query.getPrice());
		}
		if (query.getBid() == null) {
			List<ProductBrand> brands = brandService.selectByCsid(query.getCid());
			model.addAttribute("bList", brands);
		}else{
			ProductBrand brand = brandService.selectById(query.getBid());
			model.addAttribute("brand", brand);
		}
		
		model.addAttribute("fList", attrs);
		model.addAttribute("sList", select);
		String pageSize = MaoUtils.readProp("portal_pageSize");
		PageBean pageBean = productService.selectProductByQuery(page,new Integer(pageSize), query);
		model.addAttribute("pageBean", pageBean);
		
		CategorySecond cs = categoryService.selectSecondById(query.getCid());
		model.addAttribute("cs",cs);
		StringBuffer url = request.getRequestURL();
		if (request.getQueryString() != null) {
		  url.append("?");
		  url.append(request.getQueryString());
		}
		model.addAttribute("urlPath",url.toString());
		return "portal/product/list";
	}
	
	//通过solr搜索商品
	@RequestMapping("/search.do")
	public String search(String kw, String para, String price, 
			String sort, Integer page,Model model,HttpServletRequest request) {
		SearchResult result = null;
		try {
			result = productService.queryProduct(kw, para, price, sort, page);
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<String> paraList = new ArrayList<String>();
		List<Product> itemList = result.getProductList();
		for (Product product : itemList) {
			paraList.addAll(product.getAttrList());
		}
		Set<String> paraSet = new HashSet<String>(paraList);
		if (StringUtils.isNotBlank(para)) {
			String[] selected = para.split(",");
			for (String string : selected) {
				paraSet.remove(string);
			}
		}
		
		model.addAttribute("paraSet", paraSet);
		model.addAttribute("result",result);
		model.addAttribute("kw", kw);
		model.addAttribute("para",para);
		model.addAttribute("price", price);
		model.addAttribute("sort", sort);
		model.addAttribute("page", page);
		StringBuffer url = request.getRequestURL();
		if (request.getQueryString() != null) {
		  url.append("?");
		  url.append(request.getQueryString());
		}
		model.addAttribute("urlPath",url.toString());
		return "portal/product/search";
	}
	
	//商品详情页面
	@RequestMapping("/detail.do")
	public String detail(Integer id,Model model,HttpServletResponse response) {
		ProductInfo info = productService.selectProductDetail(id);
		model.addAttribute("info", info);
		FeedbackLever leverNum = feedbackService.selectLeverNum(id);
		model.addAttribute("leverNum", leverNum);
		
		//保存浏览的商品分类id
		String likeKey = MaoUtils.readProp("cookie_likeKey");
		String categoryId = info.getCatId().toString();
		Cookie cookie = new Cookie(likeKey, categoryId);
		cookie.setMaxAge(Integer.MAX_VALUE);
		cookie.setPath("/");
		response.addCookie(cookie);
		return "portal/product/detail";
	}
	
	//ajax查询库存
	@RequestMapping("/getSkuById.do")
	public void getSkuById(Integer skuId,HttpServletResponse response) {
		ProductSku sku = skuService.selectById(skuId);
		JSONObject jo = new JSONObject();
		jo.put("stock", sku.getStock());
		jo.put("shopPrice", sku.getShopPrice());
		jo.put("marketPrice", sku.getMarketPrice());
		String result = jo.toString();
		MaoUtils.printJSON(result, response);
	}
	
	//查询商品评价
	@RequestMapping("/listFeedback.do")
	public String listFeedback(Integer page,Integer pid,Integer level,Model model) {
		if (page == null) {
			page = 1;
		}
		PageBean pageBean = feedbackService.selectByPage(page,5,pid,level);
		model.addAttribute("pageBean", pageBean);
		model.addAttribute("flevel", level);
		model.addAttribute("fpid", pid);
		return "portal/product/feedback";
	}
}
