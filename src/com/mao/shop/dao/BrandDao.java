package com.mao.shop.dao;

import java.util.List;

import com.mao.shop.po.ProductBrand;

public interface BrandDao {

	/**
	 * ��ѯƷ�Ƽ�¼����
	 * @return
	 */
	int selectBrandCount();

	/**
	 * ���ݷ�ҳ��Ϣ��ѯ����
	 * @param begin �ӵڼ�����
	 * @param pageSize �������
	 * @return
	 */
	List<ProductBrand> selectBrandByPage(int begin, int pageSize);

	/**
	 * ɾ��һ��Ʒ����Ϣ
	 * @param bid Ʒ��id
	 */
	void delete(Integer bid);

	/**
	 * �������Ʋ�ѯ��Ϣ
	 * @param brandName
	 * @return
	 */
	List<ProductBrand> selectBrandByName(String brandName);

	/**
	 * ����һ��Ʒ����Ϣ
	 * @param brand
	 */
	void insertBrand(ProductBrand brand);

	/**
	 * ����id����Ϣ
	 * @param bid
	 * @return
	 */
	ProductBrand selectBrandById(Integer bid);

	/**
	 * ����Ʒ����Ϣ
	 * @param brand
	 */
	void update(ProductBrand brand);

	/**
	 * ��ѯ������ĸ����
	 * @return
	 */
	List<String> selectSortList();

	/**
	 * ����������ĸ��ѯ��Ʒ����
	 * @param sortChar
	 * @return
	 */
	List<ProductBrand> selectBrandBySortChar(String sortChar);

	/**
	 * ��ѯ�����µ�����Ʒ��
	 * @param cid
	 * @return
	 */
	List<ProductBrand> selectByCsid(Integer cid);

}
