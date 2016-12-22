package com.mao.shop.service;

import java.util.List;

import com.mao.shop.po.ProductAttr;
import com.mao.shop.utils.PageBean;

public interface FeatureService {

	/**
	 * 分页查询商品属性值信息
	 * @param pageNum
	 * @param integer 
	 * @return
	 */
	PageBean selectFeatureByPage(Integer pageNum, Integer integer);

	/**
	 * 查找某个二级分类下的属性名称是否存在
	 * @param attrName 属性名称
	 * @param csid	二级分类id
	 * @return
	 */
	List<ProductAttr> selectByAttrNameforCs(String attrName, Integer csid);

	/**
	 * 保存属性信息
	 * @param attr
	 */
	void saveAttr(ProductAttr attr);

	/**
	 * 根据属性id查询属性信息
	 * @param featid
	 * @return
	 */
	ProductAttr selectById(Integer featid);

	/**
	 * 修改属性信息
	 * @param attr
	 */
	void modify(ProductAttr attr);

	/**
	 * 根据id删除属性信息
	 * @param featId
	 */
	void deleteAttr(Integer featId);

	/**
	 * 带筛选值分页查询属性值
	 * @param pageNum
	 * @param cateId
	 * @param integer 
	 * @return
	 */
	PageBean selectByPageAndCsid(Integer pageNum, Integer cateId, Integer integer);

	/**
	 * 查询普通/规格属性
	 * @param isSpec 0普通1规格
	 * @param cateId 二级分类id
	 * @return
	 */
	List<ProductAttr> selectFeatureIsSpec(Integer isSpec,Integer cateId);

	/**
	 * 查询筛选属性
	 * @param cid
	 * @return
	 */
	List<ProductAttr> selectAttrByCsid(Integer cid);

}
