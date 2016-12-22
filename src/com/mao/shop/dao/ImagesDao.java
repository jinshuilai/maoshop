package com.mao.shop.dao;

import java.util.List;

import com.mao.shop.po.ProductImage;

public interface ImagesDao {

	/**
	 * ������ƷͼƬ
	 * @param imgList
	 * @param id ��Ʒid
	 */
	void saveImgs(List<ProductImage> imgList, Integer id);

	/**
	 * ɾ����ƷͼƬ
	 * @param pId ��Ʒid
	 */
	void deleteByPid(Integer pId);

}
