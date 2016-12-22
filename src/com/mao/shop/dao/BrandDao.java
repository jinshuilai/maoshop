package com.mao.shop.dao;

import java.util.List;

import com.mao.shop.po.ProductBrand;

public interface BrandDao {

	/**
	 * 查询品牌记录条数
	 * @return
	 */
	int selectBrandCount();

	/**
	 * 根据分页信息查询数据
	 * @param begin 从第几条查
	 * @param pageSize 查多少条
	 * @return
	 */
	List<ProductBrand> selectBrandByPage(int begin, int pageSize);

	/**
	 * 删除一条品牌信息
	 * @param bid 品牌id
	 */
	void delete(Integer bid);

	/**
	 * 根据名称查询信息
	 * @param brandName
	 * @return
	 */
	List<ProductBrand> selectBrandByName(String brandName);

	/**
	 * 插入一条品牌信息
	 * @param brand
	 */
	void insertBrand(ProductBrand brand);

	/**
	 * 根据id查信息
	 * @param bid
	 * @return
	 */
	ProductBrand selectBrandById(Integer bid);

	/**
	 * 更新品牌信息
	 * @param brand
	 */
	void update(ProductBrand brand);

	/**
	 * 查询排序字母集合
	 * @return
	 */
	List<String> selectSortList();

	/**
	 * 根据排序字母查询商品集合
	 * @param sortChar
	 * @return
	 */
	List<ProductBrand> selectBrandBySortChar(String sortChar);

	/**
	 * 查询分类下的所有品牌
	 * @param cid
	 * @return
	 */
	List<ProductBrand> selectByCsid(Integer cid);

}
