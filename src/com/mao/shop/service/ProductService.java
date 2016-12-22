package com.mao.shop.service;

import java.util.List;

import com.mao.shop.po.Product;
import com.mao.shop.po.ProductAttrValue;
import com.mao.shop.po.ProductImage;
import com.mao.shop.po.ProductInfo;
import com.mao.shop.po.ProductSku;
import com.mao.shop.po.ProductSnapshot;
import com.mao.shop.po.SearchResult;
import com.mao.shop.utils.PageBean;
import com.mao.shop.utils.ProductQuery;

public interface ProductService {

	/**
	 * 查询未上架的商品，带分页
	 * 
	 * @return
	 */
	PageBean selectBySaleStatusWithPage(Integer pageNum, Integer pageSize, Integer showStatus);

	/**
	 * 保存商品信息
	 * 
	 * @param product
	 * @param imgList
	 * @param paraList
	 * @param skuList
	 */
	void saveProduct(Product product, List<ProductImage> imgList, List<ProductAttrValue> paraList,
			List<ProductSku> skuList);

	/**
	 * 查询商品信息，包括分类、品牌、图片、属性、销售单元和规格
	 * 
	 * @param pid
	 *            商品id
	 * @return
	 */
	ProductInfo selectProductDetail(Integer pid);

	/**
	 * 改变上下架状态
	 * 
	 * @param pid
	 *            商品id
	 * @param i
	 *            0变为下架，1变为上架
	 */
	void updateShowStatus(Integer pid, int i);

	/**
	 * 删除商品信息
	 * 
	 * @param pId
	 */
	void deleteProduct(Integer pId);

	/**
	 * 前台根据查询条件查询商品
	 * 
	 * @param page
	 * @param pageSize
	 * @param query
	 * @return
	 */
	PageBean selectProductByQuery(Integer page, Integer integer, ProductQuery query);

	/**
	 * 修改商品信息
	 * 
	 * @param product
	 * @param imgList
	 * @param paraList
	 * @param skuList
	 */
	void updateProduct(Product product, List<ProductImage> imgList, List<ProductAttrValue> paraList,
			List<ProductSku> skuList);

	/**
	 * 保存交易快照
	 * 
	 * @param snapshot
	 */
	void saveSnapshot(ProductSnapshot snapshot);

	/**
	 * 查询交易快照
	 * 
	 * @param id
	 * @return
	 */
	ProductSnapshot selectSnapshot(Integer id);

	/**
	 * 到solr中搜索商品信息
	 * @param queryString 主查询条件
	 * @param para	筛选条件
	 * @param price 价格
	 * @param sort  排序
	 * @param page  当前页面
	 * @return
	 * @throws Exception
	 */
	SearchResult queryProduct(String queryString, String para, String price, 
			String sort, Integer page) throws Exception;

	/**
	 * 查询最贵的商品
	 * @return
	 */
	List<Product> selectExpensiveProduct();

	/**
	 * 查询新增的商品
	 * @return
	 */
	List<Product> selectNewProduct();

	/**
	 * 按分类查询商品
	 * @param integer
	 * @return
	 */
	List<Product> selectByCsid(Integer integer);
}
