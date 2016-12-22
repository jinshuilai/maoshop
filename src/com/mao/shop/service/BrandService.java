package com.mao.shop.service;

import java.util.List;

import com.mao.shop.po.ProductBrand;
import com.mao.shop.utils.PageBean;

public interface BrandService {

	/**
	 * ��ҳ��ѯƷ��
	 * @param pageNum ��ǰҳ��
	 * @return
	 */
	PageBean selectBrandByPage(Integer pageNum);

	/**
	 * ɾ��һ��Ʒ����Ϣ
	 * @param bid
	 */
	void delete(Integer bid);

	/**
	 * �������Ʋ�ѯƷ����Ϣ����֤Ʒ�������ظ���
	 * @param brandName Ʒ������
	 * @return
	 */
	List<ProductBrand> selectByName(String brandName);

	/**
	 * ����һ��Ʒ����Ϣ
	 * @param brand
	 */
	void save(ProductBrand brand);

	/**
	 * ����id��ѯƷ����Ϣ
	 * @param bid
	 * @return
	 */
	ProductBrand selectById(Integer bid);

	/**
	 * �޸�Ʒ����Ϣ
	 * @param brand
	 */
	void modify(ProductBrand brand);

	/**
	 * ��ѯ������ĸ����
	 * @return
	 */
	List<String> selectSortList();

	/**
	 * ����������ĸ������Ʒ����
	 * @param sortChar
	 * @return
	 */
	List<ProductBrand> selectBySortChar(String sortChar);

	/**
	 * ��ѯĳ�����µ�����Ʒ��
	 * @param cid
	 * @return
	 */
	List<ProductBrand> selectByCsid(Integer cid);

}
