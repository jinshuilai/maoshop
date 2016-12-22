package com.mao.shop.dao;

import java.util.List;

import com.mao.shop.po.ProductAttr;
import com.mao.shop.po.ProductAttrValue;

public interface FeatureDao {

	/**
	 * 查询属性值的记录总数
	 * @return
	 */
	int selectFeatureCount();

	/**
	 * 根据开始和大小查询属性值记录
	 * @param begin
	 * @param pageSize
	 * @return
	 */
	List<ProductAttr> selectFeatureByPage(int begin, int pageSize);

	/**
	 * 查询二级分类下的属性名称，存在则返回，精确查找
	 * @param attrName
	 * @param csid
	 * @return
	 */
	List<ProductAttr> selectByAttrNameForCs(String attrName, Integer csid);

	/**
	 * 插入一条商品属性信息
	 * @param attr
	 */
	void insertAttr(ProductAttr attr);

	/**
	 * 根据属性id查询属性
	 * @param featid
	 * @return
	 */
	ProductAttr selectById(Integer featid);

	/**
	 * 更新属性信息
	 * @param attr
	 */
	void updateAttr(ProductAttr attr);

	/**
	 * 删除商品信息
	 * @param featId
	 */
	void deleteById(Integer featId);

	/**
	 * 查询某分类下的属性记录条数
	 * @param cateId
	 * @return
	 */
	int selectCountByCaid(Integer cateId);

	/**
	 * 查询某分类的属性信息带分页
	 * @param begin
	 * @param pageSize
	 * @param cateId
	 * @return
	 */
	List<ProductAttr> selectByPageAndCsid(int begin, int pageSize, Integer cateId);

	/**
	 * 查询普通/规格属性
	 * @param isSpec 0普通1规格
	 * @param cateId 二级分类id
	 * @return
	 */
	List<ProductAttr> selectSpecOrNot(Integer isSpec,Integer cateId);

	/**
	 * 保存商品的属性
	 * @param paraList
	 * @param id
	 */
	void saveAttrValue(List<ProductAttrValue> paraList, Integer id);

	/**
	 * 根据商品id删除商品属性
	 * @param pId
	 */
	void deleteByPid(Integer pId);

	/**
	 * 查询筛选条件
	 * @param cid
	 * @return
	 */
	List<ProductAttr> selectAttrByCsid(Integer cid);

	

}
