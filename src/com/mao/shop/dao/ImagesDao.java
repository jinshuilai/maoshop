package com.mao.shop.dao;

import java.util.List;

import com.mao.shop.po.ProductImage;

public interface ImagesDao {

	/**
	 * 保存商品图片
	 * @param imgList
	 * @param id 商品id
	 */
	void saveImgs(List<ProductImage> imgList, Integer id);

	/**
	 * 删除商品图片
	 * @param pId 商品id
	 */
	void deleteByPid(Integer pId);

}
