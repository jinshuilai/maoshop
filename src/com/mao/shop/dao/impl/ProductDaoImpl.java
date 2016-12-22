package com.mao.shop.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mao.shop.dao.ProductDao;
import com.mao.shop.po.Product;
import com.mao.shop.po.ProductInfo;
import com.mao.shop.po.SearchResult;

@Repository
public class ProductDaoImpl extends SqlSessionDaoSupport implements ProductDao{

	@Autowired
	private SolrClient solrClient;
	@Autowired
	@Override
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		super.setSqlSessionFactory(sqlSessionFactory);
	}
	
	String ns = "com.mao.shop.mapper.ProductMapper.";

	@Override
	public int selectProductCount(Integer showStatus) {
		
		return getSqlSession().selectOne(ns+"selectCountByShowStatus", showStatus);
	}

	@Override
	public List<Product> selectBySaleStatusWithPage(int begin, int pageSize, Integer showStatus) {
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("showStatus", showStatus);
		map.put("begin", begin);
		map.put("size", pageSize);
		return getSqlSession().selectList(ns+"selectByShowStatus", map);
	}

	@Override
	public void saveProduct(Product product) {
		getSqlSession().insert(ns+"insert", product);
	}

	@Override
	public ProductInfo selectProductDetail(Integer pid) {
		
		return getSqlSession().selectOne(ns+"selectDetail", pid);
	}

	@Override
	public void updateShowStatus(Integer pid, int i) {
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("pid", pid);
		map.put("status", i);
		getSqlSession().update(ns+"updateShowStatus", map);
	}

	@Override
	public void deleteByPid(Integer pId) {
		getSqlSession().delete(ns+"deleteByPrimaryKey", pId);
	}

	@Override
	public List<Product> selectProductByQuery(int begin, Integer pageSize, Map<String, Object> map) {
		map.put("begin", begin);
		map.put("size", pageSize);
		return getSqlSession().selectList(ns+"selectProductByQuery", map);
	}

	@Override
	public int selectCountByQuery(Map<String, Object> map) {
		
		return getSqlSession().selectOne(ns+"selectCountByQuery", map);
	}

	@Override
	public void updateProduct(Product product) {
		getSqlSession().update(ns+"updateByPrimaryKeyWithBLOBs", product);
	}

	@Override
	public SearchResult queryProduct(SolrQuery query) throws Exception {
		QueryResponse queryResponse = solrClient.query(query);
		SolrDocumentList results = queryResponse.getResults();
		
		List<Product> productList = new ArrayList<Product>();
		for (SolrDocument solrDocument : results) {
			Product product = new Product();
			String idstr =  (String) solrDocument.get("id");
			product.setProId(Integer.parseInt(idstr));
			//»°∏ﬂ¡¡œ‘ æ
			Map<String, Map<String, List<String>>> highlighting = queryResponse.getHighlighting();
			List<String> list = (highlighting.get(solrDocument.get("id")).get("product_name"));
			String productName = "";
			if (null != list && list.size() > 0) {
				productName = list.get(0);
			}else {
				productName = (String) solrDocument.get("product_name");
			}
			product.setPname(productName);
			float price = (float) solrDocument.get("product_price");
			product.setPrice(new BigDecimal(price));
			String picture = (String) solrDocument.get("product_picture");
			product.setImgs(picture);
			List<String> paraList = new ArrayList<String>();
			paraList = (List<String>) solrDocument.get("product_para");
			product.setAttrList(paraList);
			productList.add(product);
		}
		SearchResult searchResult = new SearchResult();
		searchResult.setProductList(productList);
		searchResult.setRecordCount(results.getNumFound());
		return searchResult;
	}

	@Override
	public List<Product> selectExpensiveProduct() {
		
		return getSqlSession().selectList(ns+"selectExpensive");
	}

	@Override
	public List<Product> selectNewProduct() {
		
		return getSqlSession().selectList(ns+"selectNewProduct");
	}

	@Override
	public List<Product> selectByCsid(Integer integer) {
		
		return getSqlSession().selectList(ns+"selectByCsid", integer);
	}

	
}
