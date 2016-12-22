package com.mao.shop.dao;

import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;

import com.mao.shop.po.Product;
import com.mao.shop.po.ProductInfo;
import com.mao.shop.po.SearchResult;

public interface ProductDao {
	
	/**
	 * �������¼�״̬��ѯ��¼��
	 * @param showStatus
	 * @return
	 */
	int selectProductCount(Integer showStatus);

	/**
	 * �������¼�״̬��ѯ��¼
	 * @param begin
	 * @param pageSize
	 * @param showStatus
	 * @return
	 */
	List<Product> selectBySaleStatusWithPage(int begin, int pageSize, Integer showStatus);

	/**
	 * ������Ʒ��Ϣ
	 * @param product
	 */
	void saveProduct(Product product);

	/**
	 * ��ѯ��Ʒ��Ϣ���������ࡢƷ�ơ�ͼƬ�����ԡ����۵�Ԫ�͹��
	 * @param pid ��Ʒid
	 * @return
	 */
	ProductInfo selectProductDetail(Integer pid);

	/**
	 * �ı����¼�״̬
	 * @param pid
	 * @param i 0��Ϊ�¼ܣ�1��Ϊ�ϼ�
	 */
	void updateShowStatus(Integer pid, int i);

	/**
	 * ɾ����Ʒ��Ϣ
	 * @param pId
	 */
	void deleteByPid(Integer pId);
	
	/**
	 * ��ѯ������������Ʒ
	 * @param begin
	 * @param pageSize
	 * @param map
	 * @return
	 */
	List<Product> selectProductByQuery(int begin, Integer pageSize, Map<String, Object> map);

	/**
	 * ��ѯ������������Ʒ����
	 * @param map
	 * @return
	 */
	int selectCountByQuery(Map<String, Object> map);

	/**
	 * ������Ʒ��Ϣ
	 * @param product
	 */
	void updateProduct(Product product);

	/**
	 * ��ѯsolr
	 * @param query
	 * @return
	 */
	SearchResult queryProduct(SolrQuery query) throws Exception;

	/**
	 * ��ѯ������Ʒ
	 * @return
	 */
	List<Product> selectExpensiveProduct();

	/**
	 * ��ѯ���µ���Ʒ
	 * @return
	 */
	List<Product> selectNewProduct();

	/**
	 * ���ݷ����ѯ��Ʒ
	 * @param integer
	 * @return
	 */
	List<Product> selectByCsid(Integer integer);
}
