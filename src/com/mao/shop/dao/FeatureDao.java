package com.mao.shop.dao;

import java.util.List;

import com.mao.shop.po.ProductAttr;
import com.mao.shop.po.ProductAttrValue;

public interface FeatureDao {

	/**
	 * ��ѯ����ֵ�ļ�¼����
	 * @return
	 */
	int selectFeatureCount();

	/**
	 * ���ݿ�ʼ�ʹ�С��ѯ����ֵ��¼
	 * @param begin
	 * @param pageSize
	 * @return
	 */
	List<ProductAttr> selectFeatureByPage(int begin, int pageSize);

	/**
	 * ��ѯ���������µ��������ƣ������򷵻أ���ȷ����
	 * @param attrName
	 * @param csid
	 * @return
	 */
	List<ProductAttr> selectByAttrNameForCs(String attrName, Integer csid);

	/**
	 * ����һ����Ʒ������Ϣ
	 * @param attr
	 */
	void insertAttr(ProductAttr attr);

	/**
	 * ��������id��ѯ����
	 * @param featid
	 * @return
	 */
	ProductAttr selectById(Integer featid);

	/**
	 * ����������Ϣ
	 * @param attr
	 */
	void updateAttr(ProductAttr attr);

	/**
	 * ɾ����Ʒ��Ϣ
	 * @param featId
	 */
	void deleteById(Integer featId);

	/**
	 * ��ѯĳ�����µ����Լ�¼����
	 * @param cateId
	 * @return
	 */
	int selectCountByCaid(Integer cateId);

	/**
	 * ��ѯĳ�����������Ϣ����ҳ
	 * @param begin
	 * @param pageSize
	 * @param cateId
	 * @return
	 */
	List<ProductAttr> selectByPageAndCsid(int begin, int pageSize, Integer cateId);

	/**
	 * ��ѯ��ͨ/�������
	 * @param isSpec 0��ͨ1���
	 * @param cateId ��������id
	 * @return
	 */
	List<ProductAttr> selectSpecOrNot(Integer isSpec,Integer cateId);

	/**
	 * ������Ʒ������
	 * @param paraList
	 * @param id
	 */
	void saveAttrValue(List<ProductAttrValue> paraList, Integer id);

	/**
	 * ������Ʒidɾ����Ʒ����
	 * @param pId
	 */
	void deleteByPid(Integer pId);

	/**
	 * ��ѯɸѡ����
	 * @param cid
	 * @return
	 */
	List<ProductAttr> selectAttrByCsid(Integer cid);

	

}
