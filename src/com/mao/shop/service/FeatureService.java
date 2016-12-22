package com.mao.shop.service;

import java.util.List;

import com.mao.shop.po.ProductAttr;
import com.mao.shop.utils.PageBean;

public interface FeatureService {

	/**
	 * ��ҳ��ѯ��Ʒ����ֵ��Ϣ
	 * @param pageNum
	 * @param integer 
	 * @return
	 */
	PageBean selectFeatureByPage(Integer pageNum, Integer integer);

	/**
	 * ����ĳ�����������µ����������Ƿ����
	 * @param attrName ��������
	 * @param csid	��������id
	 * @return
	 */
	List<ProductAttr> selectByAttrNameforCs(String attrName, Integer csid);

	/**
	 * ����������Ϣ
	 * @param attr
	 */
	void saveAttr(ProductAttr attr);

	/**
	 * ��������id��ѯ������Ϣ
	 * @param featid
	 * @return
	 */
	ProductAttr selectById(Integer featid);

	/**
	 * �޸�������Ϣ
	 * @param attr
	 */
	void modify(ProductAttr attr);

	/**
	 * ����idɾ��������Ϣ
	 * @param featId
	 */
	void deleteAttr(Integer featId);

	/**
	 * ��ɸѡֵ��ҳ��ѯ����ֵ
	 * @param pageNum
	 * @param cateId
	 * @param integer 
	 * @return
	 */
	PageBean selectByPageAndCsid(Integer pageNum, Integer cateId, Integer integer);

	/**
	 * ��ѯ��ͨ/�������
	 * @param isSpec 0��ͨ1���
	 * @param cateId ��������id
	 * @return
	 */
	List<ProductAttr> selectFeatureIsSpec(Integer isSpec,Integer cateId);

	/**
	 * ��ѯɸѡ����
	 * @param cid
	 * @return
	 */
	List<ProductAttr> selectAttrByCsid(Integer cid);

}
