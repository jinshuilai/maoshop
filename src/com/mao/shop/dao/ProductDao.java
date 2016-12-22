package com.mao.shop.dao;

import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;

import com.mao.shop.po.Product;
import com.mao.shop.po.ProductInfo;
import com.mao.shop.po.SearchResult;

public interface ProductDao {
	
	/**
	 * 根据上下架状态查询记录数
	 * @param showStatus
	 * @return
	 */
	int selectProductCount(Integer showStatus);

	/**
	 * 根据上下架状态查询记录
	 * @param begin
	 * @param pageSize
	 * @param showStatus
	 * @return
	 */
	List<Product> selectBySaleStatusWithPage(int begin, int pageSize, Integer showStatus);

	/**
	 * 保存商品信息
	 * @param product
	 */
	void saveProduct(Product product);

	/**
	 * 查询商品信息，包括分类、品牌、图片、属性、销售单元和规格
	 * @param pid 商品id
	 * @return
	 */
	ProductInfo selectProductDetail(Integer pid);

	/**
	 * 改变上下架状态
	 * @param pid
	 * @param i 0变为下架，1变为上架
	 */
	void updateShowStatus(Integer pid, int i);

	/**
	 * 删除商品信息
	 * @param pId
	 */
	void deleteByPid(Integer pId);
	
	/**
	 * 查询符合条件的商品
	 * @param begin
	 * @param pageSize
	 * @param map
	 * @return
	 */
	List<Product> selectProductByQuery(int begin, Integer pageSize, Map<String, Object> map);

	/**
	 * 查询符合条件的商品数量
	 * @param map
	 * @return
	 */
	int selectCountByQuery(Map<String, Object> map);

	/**
	 * 更新商品信息
	 * @param product
	 */
	void updateProduct(Product product);

	/**
	 * 查询solr
	 * @param query
	 * @return
	 */
	SearchResult queryProduct(SolrQuery query) throws Exception;

	/**
	 * 查询最贵的商品
	 * @return
	 */
	List<Product> selectExpensiveProduct();

	/**
	 * 查询最新的商品
	 * @return
	 */
	List<Product> selectNewProduct();

	/**
	 * 根据分类查询商品
	 * @param integer
	 * @return
	 */
	List<Product> selectByCsid(Integer integer);
}
