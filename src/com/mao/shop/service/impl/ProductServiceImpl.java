package com.mao.shop.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mao.shop.dao.FeatureDao;
import com.mao.shop.dao.ImagesDao;
import com.mao.shop.dao.ProductDao;
import com.mao.shop.dao.SkuDao;
import com.mao.shop.dao.SnapshotDao;
import com.mao.shop.po.Product;
import com.mao.shop.po.ProductAttrValue;
import com.mao.shop.po.ProductImage;
import com.mao.shop.po.ProductInfo;
import com.mao.shop.po.ProductSku;
import com.mao.shop.po.ProductSnapshot;
import com.mao.shop.po.SearchResult;
import com.mao.shop.service.ProductService;
import com.mao.shop.utils.MaoUtils;
import com.mao.shop.utils.PageBean;
import com.mao.shop.utils.ProductQuery;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDao productDao;
	@Autowired
	private ImagesDao imagesDao;
	@Autowired
	private FeatureDao featureDao;
	@Autowired
	private SkuDao skudao;
	@Autowired
	private SnapshotDao snapshotDao;

	@Override
	public PageBean selectBySaleStatusWithPage(Integer pageNum,Integer pageSize,Integer showStatus) {
		int count = 0;
		count = productDao.selectProductCount(showStatus);
		
		PageBean pageBean = new PageBean();
		pageBean.setPageSize(pageSize);
		pageBean.setPageNum(pageNum);
		pageBean.setTotalCount(count);
		
		List<Product> attrs = productDao.selectBySaleStatusWithPage(pageBean.getBegin(),pageBean.getPageSize(),showStatus);
		pageBean.setDataList(attrs);
		
		return pageBean;
	}

	@Override
	public void saveProduct(Product product, List<ProductImage> imgList, List<ProductAttrValue> paraList,
			List<ProductSku> skuList) {
		//先保存商品，得到商品返回的主键
		productDao.saveProduct(product);
		//保存图片
		imagesDao.saveImgs(imgList,product.getProId());
		//保存属性
		featureDao.saveAttrValue(paraList,product.getProId());
		//保存规格
		skudao.saveSku(skuList,product.getProId());
	}

	@Override
	public ProductInfo selectProductDetail(Integer pid) {
		
		return productDao.selectProductDetail(pid);
	}

	@Override
	public void updateShowStatus(Integer pid, int i) {
		productDao.updateShowStatus(pid,i);
	}

	@Override
	public void deleteProduct(Integer pId) {
		imagesDao.deleteByPid(pId);
		skudao.deleteByPid(pId);
		featureDao.deleteByPid(pId);
		productDao.deleteByPid(pId);
	}

	@Override
	public PageBean selectProductByQuery(Integer page, Integer pageSize, ProductQuery query) {
		Map<String, Object> map = query.getQueryMap();
		
		int count = 0;
		count = productDao.selectCountByQuery(map);
		
		PageBean pageBean = new PageBean();
		pageBean.setPageSize(pageSize);
		pageBean.setPageNum(page);
		pageBean.setTotalCount(count);
		
		List<Product> attrs = productDao.selectProductByQuery(pageBean.getBegin(),pageSize,map);
		pageBean.setDataList(attrs);
		
		return pageBean;
	}

	@Override
	public void updateProduct(Product product, List<ProductImage> imgList, List<ProductAttrValue> paraList,
			List<ProductSku> skuList) {
		product.setUpdatedAt(new Date());
		Integer pId = product.getProId();
		imagesDao.deleteByPid(pId);
		skudao.deleteByPid(pId);
		featureDao.deleteByPid(pId);
		productDao.updateProduct(product);
		//保存图片
		imagesDao.saveImgs(imgList,product.getProId());
		//保存属性
		featureDao.saveAttrValue(paraList,product.getProId());
		//保存规格
		skudao.saveSku(skuList,product.getProId());
	}

	@Override
	public void saveSnapshot(ProductSnapshot snapshot) {
		snapshotDao.save(snapshot);
	}

	@Override
	public ProductSnapshot selectSnapshot(Integer id) {
		
		return snapshotDao.select(id);
	}

	@Override
	public SearchResult queryProduct(String queryString, String para, String price, String sort, Integer page)
			throws Exception {
		SolrQuery query = new SolrQuery();
		if (StringUtils.isNotBlank(queryString)) {
			query.setQuery(queryString);
		}else {
			query.setQuery("*:*");
		}
		if (StringUtils.isNotBlank(para)) {
			String[] paras = para.split(",");
			for (String string : paras) {
				query.addFilterQuery("product_para:" + string);
			}
		}
		if (StringUtils.isNotBlank(price)) {
			String[] strings = price.split("-");
			query.addFilterQuery("product_price:["+strings[0]+" TO "+strings[1]+"]");
		}
		if ("1".equals(sort)) {
			query.setSort("product_price",ORDER.desc);
		}else if("2".equals(sort)){
			query.setSort("product_price",ORDER.asc);
		}
		if (null == page) {
			page = 1;
		}
		String pageSize = MaoUtils.readProp("portal_pageSize");
		int start = (page - 1) * Integer.parseInt(pageSize);
		query.setStart(start);
		query.setRows(Integer.parseInt(pageSize));
		query.set("df", "product_keywords");
		query.setHighlight(true);
		query.addHighlightField("product_name");
		query.setHighlightSimplePre("<span style=\"color:red\">");
		query.setHighlightSimplePost("</span>");

		SearchResult searchResult = productDao.queryProduct(query);
		searchResult.setCurPage(page);
		return searchResult;
	}

	@Override
	public List<Product> selectExpensiveProduct() {
		
		return productDao.selectExpensiveProduct();
	}

	@Override
	public List<Product> selectNewProduct() {
		
		return productDao.selectNewProduct();
	}

	@Override
	public List<Product> selectByCsid(Integer integer) {
		
		return productDao.selectByCsid(integer);
	}
}
