package com.mao.shop.service;

import java.util.List;

import com.mao.shop.po.ProductBrand;
import com.mao.shop.utils.PageBean;

public interface BrandService {

	/**
	 * 分页查询品牌
	 * @param pageNum 当前页码
	 * @return
	 */
	PageBean selectBrandByPage(Integer pageNum);

	/**
	 * 删除一条品牌信息
	 * @param bid
	 */
	void delete(Integer bid);

	/**
	 * 根据名称查询品牌信息，验证品牌名称重复性
	 * @param brandName 品牌名称
	 * @return
	 */
	List<ProductBrand> selectByName(String brandName);

	/**
	 * 保存一条品牌信息
	 * @param brand
	 */
	void save(ProductBrand brand);

	/**
	 * 根据id查询品牌信息
	 * @param bid
	 * @return
	 */
	ProductBrand selectById(Integer bid);

	/**
	 * 修改品牌信息
	 * @param brand
	 */
	void modify(ProductBrand brand);

	/**
	 * 查询排序字母集合
	 * @return
	 */
	List<String> selectSortList();

	/**
	 * 根据排序字母查找商品集合
	 * @param sortChar
	 * @return
	 */
	List<ProductBrand> selectBySortChar(String sortChar);

	/**
	 * 查询某分类下的所有品牌
	 * @param cid
	 * @return
	 */
	List<ProductBrand> selectByCsid(Integer cid);

}
