package com.mao.shop.controller;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mao.shop.po.Category;
import com.mao.shop.po.CategorySecond;
import com.mao.shop.po.Product;
import com.mao.shop.po.ProductAttr;
import com.mao.shop.po.ProductAttrValue;
import com.mao.shop.po.ProductBrand;
import com.mao.shop.po.ProductImage;
import com.mao.shop.po.ProductInfo;
import com.mao.shop.po.ProductSku;
import com.mao.shop.po.ProductSpec;
import com.mao.shop.service.BrandService;
import com.mao.shop.service.CategoryService;
import com.mao.shop.service.FeatureService;
import com.mao.shop.service.ProductService;
import com.mao.shop.utils.MaoUtils;
import com.mao.shop.utils.PageBean;

@Controller
@RequestMapping("/item")
public class ItemController {

	@Autowired
	private BrandService brandService;
	@Autowired
	private FeatureService featureService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private ProductService productService;
	
	//品牌信息页
	@RequestMapping("/brand.do")
	public String toBrand(Integer pageNum,Model model) {
		if (pageNum == null) {
			pageNum = 1;
		}
		PageBean pageBean = brandService.selectBrandByPage(pageNum);
		model.addAttribute("pageBean", pageBean);
		return "console/item/brand";
	}
	
	//品牌添加页面
	@RequestMapping("/toAddBrand.do")
	public String toAddBrand() {
		
		return "console/item/addbrand";
	}
	
	//删除品牌信息
	@RequestMapping("/delBrand.do")
	public String delBrand(Integer bid){
		brandService.delete(bid);
		return "redirect:brand.do";
	}
	
	//验证品牌名称是否存在
	@RequestMapping("/validBrandName.do")
	public void validBrandName(String brandName,PrintWriter out) {
		List<ProductBrand> list = brandService.selectByName(brandName);
		if (list != null && list.size() > 0 ) {			
			out.write("no");
		}else {
			out.write("yes");
		}
	}
	
	//添加品牌
	@RequestMapping("/addBrand.do")
	public String addBrand(ProductBrand brand) {
		brandService.save(brand);
		return "redirect:brand.do";
	}
	
	//修改品牌信息页面
	@RequestMapping("/toEditBrand.do")
	public String toEditBrand(Integer bid,Model model) {
		ProductBrand brand = brandService.selectById(bid);
		model.addAttribute("brand", brand);
		return "console/item/addbrand";
	}
	
	//修改品牌信息
	@RequestMapping("/modifyBrand.do")
	public String modifyBrand(ProductBrand brand) {
		brandService.modify(brand);
		return "redirect:brand.do";
	}
	
	//商品属性值页面
	@RequestMapping("/feature.do")
	public String feature(Integer pageNum,Integer cateId,Model model) {
		if (pageNum == null) {
			pageNum = 1;
		}
		String pageSize = MaoUtils.readProp("console_pageSize");
		if(cateId == null){
			PageBean pageBean = featureService.selectFeatureByPage(pageNum,new Integer(pageSize));
			model.addAttribute("pageBean", pageBean);
		}else{
			//根据二级id查出分页信息
			PageBean pageBean = featureService.selectByPageAndCsid(pageNum,cateId,new Integer(pageSize));
			model.addAttribute("pageBean", pageBean);
			//attr回显查询条件的一级和二级id
			ProductAttr attr = new ProductAttr();
			CategorySecond cs = categoryService.selectSecondById(cateId);
			attr.setCatId(cs.getParentId());
			attr.setCateId(cateId);
			model.addAttribute("attr", attr);
			//当前选择的二级分类列表回显
			List<CategorySecond> cateList = categoryService.selectSecondByParentId(cs.getParentId());
			model.addAttribute("cateList", cateList);
		}
		List<Category> catList = categoryService.selectAll();
		model.addAttribute("catList", catList);
		return "console/item/feature";
	}
	
	//添加商品属性页
	@RequestMapping("/toAddFeature.do")
	public String toAddFeature(Model model) {
		List<Category> catList = categoryService.selectAll();
		model.addAttribute("catList", catList);
		return "console/item/addfeature";
	}
	
	//查询二级分类下的属性名是否重复了
	@RequestMapping("/validAttrName.do")
	public void validAttrName(String attrName,Integer csid,PrintWriter out) {
		List<ProductAttr> attrs = featureService.selectByAttrNameforCs(attrName,csid);
		if (attrs != null && attrs.size() > 0) {
			out.write("no");
		}else {
			out.write("yes");
		}
	}
	
	//添加商品属性
	@RequestMapping("/addFeature.do")
	public String addFeature(ProductAttr attr) {
		featureService.saveAttr(attr);
		return "redirect:feature.do";
	}
	
	//编辑属性信息页
	@RequestMapping("/toEditFeature.do")
	public String toEditFeature(Integer featId,Model model) {
		ProductAttr attr = featureService.selectById(featId);
		CategorySecond cs = categoryService.selectSecondById(attr.getCateId());
		attr.setCatId(cs.getParentId());
		model.addAttribute("attr", attr);
		List<CategorySecond> cateList = categoryService.selectSecondByParentId(cs.getParentId());
		model.addAttribute("cateList", cateList);
		List<Category> catList = categoryService.selectAll();
		model.addAttribute("catList", catList);
		return "console/item/addfeature";
	}
	
	//修改属性信息
	@RequestMapping("/modifyFeature.do")
	public String modifyFeature(ProductAttr attr) {
		featureService.modify(attr);
		return "redirect:feature.do";
	}
	
	//删除属性信息
	@RequestMapping("/delFeature.do")
	public String delFeature(Integer featId) {
		featureService.deleteAttr(featId);
		return "redirect:feature.do";
	}
	
	//商品信息页
	@RequestMapping("/product.do")
	public String product(Integer pageNum,Model model){
		if(pageNum == null){
			pageNum = 1;
		}
		String pageSize = MaoUtils.readProp("console_pageSize");
		PageBean pageBean = productService.selectBySaleStatusWithPage(pageNum,new Integer(pageSize),0);
		model.addAttribute("pageBean", pageBean);
		return "console/item/product";
	}
	
	//添加商品页
	@RequestMapping("/toAddProduct.do")
	public String toAddProduct(Model model) {
		List<Category> catList = categoryService.selectAll();
		model.addAttribute("catList", catList);
		return "console/item/addproduct";
	}
	
	//ajax查询品牌返回json
	@RequestMapping("/loadBrand.do")
	public void loadBrand(String sortChar,HttpServletResponse response) {
		List<ProductBrand> bList = brandService.selectBySortChar(sortChar);
		JSONObject jo = new JSONObject();
		jo.accumulate("bList", bList);
		String result = jo.toString();
		MaoUtils.printJSON(result, response);
	}
	
	//填写商品信息页
	@RequestMapping("/toEditProduct.do")
	public String toEditProduct(Integer cateId,Model model) {
		if (cateId == null) {
			return "redirect:toAddProduct.do";
		}
		CategorySecond cs = categoryService.selectSecondById(cateId);
		model.addAttribute("cs", cs);
		
		List<String> charList = brandService.selectSortList();
		model.addAttribute("charList", charList);
		
		List<ProductAttr> commList = featureService.selectFeatureIsSpec(0,cateId);
		model.addAttribute("commList", commList);
		
		List<ProductAttr> specList = featureService.selectFeatureIsSpec(1, cateId);
		model.addAttribute("specList", specList);
		return "console/item/editproduct";
	}
	
	//添加商品信息
	@RequestMapping("/addProduct.do")
	public String addProduct(Product product,HttpServletRequest request,Integer divNum) {
		product.setOnSaleAt(new Date());
		product.setShowStatus((byte) 0);
		//接收图片信息
		List<ProductImage> imgList = new ArrayList<ProductImage>();	
		for(int i = 1;i <= 3;i++){
			String path = request.getParameter("filePath"+i);
			ProductImage image = new ProductImage();
			image.setFilepath(path);
			image.setSortOrder((byte)i);
			imgList.add(image);
		}
		//接收普通属性
		List<ProductAttrValue> paraList = new ArrayList<ProductAttrValue>();
		List<ProductAttr> commList = featureService.selectFeatureIsSpec(0, product.getCatId());
		for (ProductAttr attr : commList) {
			Integer featId = attr.getPaId();
			//复选框的值要拼接
			if (attr.getAttrType() == 2) {
				String[] attrValues = request.getParameterValues(featId+"");
				if (attrValues != null && attrValues.length > 0) {
					String paraVals = "";
					for (String string : attrValues) {
						paraVals = paraVals + string +",";
					}
					paraVals = paraVals.substring(0, paraVals.length() - 1);
					ProductAttrValue pav = new ProductAttrValue();
					pav.setAttrId(featId);
					pav.setAttrValue(paraVals);
					paraList.add(pav);
				}
			}else{
				String para = request.getParameter(featId+"");
				if (StringUtils.isNotBlank(para)) {
					ProductAttrValue av = new ProductAttrValue();
					av.setAttrId(featId);
					av.setAttrValue(para);
					paraList.add(av);
				}
			}			
		}
		//接收规格
		List<ProductSku> skuList = new ArrayList<ProductSku>();
		List<ProductAttr> specList = featureService.selectFeatureIsSpec(1, product.getCatId());
		for (int i = 0; i <= divNum; i++) {
			String shopPrice = request.getParameter("shopPrice"+i);
			String stock = request.getParameter("stock"+i);
			if (StringUtils.isNotBlank(shopPrice) && StringUtils.isNotBlank(stock)) {
				String marketPrice = request.getParameter("marketPrice"+i);
				String skuName = request.getParameter("skuName"+i);
				
				ProductSku sku = new ProductSku();
				if (StringUtils.isNotBlank(marketPrice)) {
					sku.setMarketPrice(new BigDecimal(marketPrice));
				}
				sku.setShopPrice(new BigDecimal(shopPrice));
				sku.setStock(new Integer(stock));
				sku.setSkuName(skuName);
				
				List<ProductSpec> psList = new ArrayList<ProductSpec>();
				for(ProductAttr feature : specList){
					Integer featureId = feature.getPaId();
					String specVal = request.getParameter(featureId+"specradio"+i);
					if (StringUtils.isNotBlank(specVal)) {
						ProductSpec spec = new ProductSpec();
						spec.setAttrid(featureId);
						spec.setAttrvalue(specVal);
						psList.add(spec);
					}
					
				}
				sku.setSpecList(psList);
				skuList.add(sku);
			}
			
		}
		productService.saveProduct(product,imgList,paraList,skuList);
		return "redirect:product.do";
	}
	
	//查看商品详情
	@RequestMapping("/seeProduct.do")
	public String seeProduct(Integer pId,Model model) {
		if (pId == null) {
			return "redirect:product.do";
		}
		ProductInfo info = productService.selectProductDetail(pId);
		model.addAttribute("info", info);

		return "console/item/seeproduct";
	}
	
	//查看可编辑商品详情
	@RequestMapping("/seeEditProduct.do")
	public String seeEditProduct(Integer pId,Model model) {
		if (pId == null) {
			return "redirect:product.do";
		}
		ProductInfo info = productService.selectProductDetail(pId);
		model.addAttribute("info", info);
		List<String> charList = brandService.selectSortList();
		model.addAttribute("charList",charList);
		ProductBrand brand = brandService.selectById(info.getBrandId());
		List<ProductBrand> brandList = brandService.selectBySortChar(brand.getSortOrder());
		model.addAttribute("brandList",brandList);
		
		List<ProductAttr> commList = featureService.selectFeatureIsSpec(0,info.getCatId());
		List<ProductAttrValue> paraList = info.getParaList();
		ProductAttr attr = new ProductAttr();
		ProductAttrValue attrValue = new ProductAttrValue();
		for(int i=0;i < paraList.size();i++){
			attr = commList.get(i);
			attrValue = paraList.get(i);
			attr.setCurrentValue(attrValue.getAttrValue());
		}
		model.addAttribute("commList", commList);
	
		List<ProductAttr> specList = featureService.selectFeatureIsSpec(1, info.getCatId());	
		model.addAttribute("specList", specList);
		int divNum = info.getSkuList().size();
		model.addAttribute("divNum", divNum);
		return "console/item/seeEditproduct";
	}
	
	//更新商品信息
	@RequestMapping("/updateProduct.do")
	public String updateProduct(Product product,HttpServletRequest request,Integer divNum) {
		product.setOnSaleAt(new Date());
		product.setShowStatus((byte) 0);
		//接收图片信息
		List<ProductImage> imgList = new ArrayList<ProductImage>();	
		for(int i = 1;i <= 3;i++){
			String path = request.getParameter("filePath"+i);
			ProductImage image = new ProductImage();
			image.setFilepath(path);
			image.setSortOrder((byte)i);
			imgList.add(image);
		}
		//接收普通属性
		List<ProductAttrValue> paraList = new ArrayList<ProductAttrValue>();
		List<ProductAttr> commList = featureService.selectFeatureIsSpec(0, product.getCatId());
		for (ProductAttr attr : commList) {
			Integer featId = attr.getPaId();
			//复选框的值要拼接
			if (attr.getAttrType() == 2) {
				String[] attrValues = request.getParameterValues(featId+"");
				if (attrValues != null && attrValues.length > 0) {
					String paraVals = "";
					for (String string : attrValues) {
						paraVals = paraVals + string +",";
					}
					paraVals = paraVals.substring(0, paraVals.length() - 1);
					ProductAttrValue pav = new ProductAttrValue();
					pav.setAttrId(featId);
					pav.setAttrValue(paraVals);
					paraList.add(pav);
				}
			}else{
				String para = request.getParameter(featId+"");
				if (StringUtils.isNotBlank(para)) {
					ProductAttrValue av = new ProductAttrValue();
					av.setAttrId(featId);
					av.setAttrValue(para);
					paraList.add(av);
				}
			}			
		}
		//接收规格
		List<ProductSku> skuList = new ArrayList<ProductSku>();
		List<ProductAttr> specList = featureService.selectFeatureIsSpec(1, product.getCatId());
		for (int i = 1; i <= divNum; i++) {
			String shopPrice = request.getParameter("shopPrice"+i);
			String stock = request.getParameter("stock"+i);
			if (StringUtils.isNotBlank(shopPrice) && StringUtils.isNotBlank(stock)) {
				String marketPrice = request.getParameter("marketPrice"+i);
				String skuName = request.getParameter("skuName"+i);
				
				ProductSku sku = new ProductSku();
				if (StringUtils.isNotBlank(marketPrice)) {
					sku.setMarketPrice(new BigDecimal(marketPrice));
				}
				sku.setShopPrice(new BigDecimal(shopPrice));
				sku.setStock(new Integer(stock));
				sku.setSkuName(skuName);
				
				List<ProductSpec> psList = new ArrayList<ProductSpec>();
				for(ProductAttr feature : specList){
					Integer featureId = feature.getPaId();
					String specVal = request.getParameter(featureId+"specradio"+i);
					ProductSpec spec = new ProductSpec();
					spec.setAttrid(featureId);
					spec.setAttrvalue(specVal);
					psList.add(spec);
				}
				sku.setSpecList(psList);
				skuList.add(sku);
			}
			
		}
		productService.updateProduct(product,imgList,paraList,skuList);
		return "redirect:product.do";
	}
	
	//商品上下架页
	@RequestMapping("/saleProduct.do")
	public String saleProduct(Integer pageNum,Integer showStatus,Model model) {
		if(pageNum == null){
			pageNum = 1;
		}
		String pageSize = MaoUtils.readProp("console_pageSize");
		PageBean pageBean = productService.selectBySaleStatusWithPage(pageNum,new Integer(pageSize),showStatus);
		model.addAttribute("pageBean", pageBean);
		model.addAttribute("show", showStatus);
		return "console/item/onsaleproduct";
	}
	
	//上架商品
	@RequestMapping("/changeShowUp.do")
	public String changeShowUp(Integer pid) {
		productService.updateShowStatus(pid,1);
		return "redirect:saleProduct.do?showStatus=0";
	}
	
	//下架商品
	@RequestMapping("/changeShowDown.do")
	public String changeShowDown(Integer pid) {
		productService.updateShowStatus(pid,0);
		return "redirect:saleProduct.do?showStatus=1";
	}
	
	//删除商品
	@RequestMapping("/delProduct.do")
	public String delProduct(Integer pId){
		productService.deleteProduct(pId);
		return "redirect:product.do";
	}
}
