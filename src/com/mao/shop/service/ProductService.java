package com.mao.shop.service;

import java.util.List;

import com.mao.shop.po.Product;
import com.mao.shop.po.ProductAttrValue;
import com.mao.shop.po.ProductImage;
import com.mao.shop.po.ProductInfo;
import com.mao.shop.po.ProductSku;
import com.mao.shop.po.ProductSnapshot;
import com.mao.shop.po.SearchResult;
import com.mao.shop.utils.PageBean;
import com.mao.shop.utils.ProductQuery;

public interface ProductService {

	/**
	 * ��ѯδ�ϼܵ���Ʒ������ҳ
	 * 
	 * @return
	 */
	PageBean selectBySaleStatusWithPage(Integer pageNum, Integer pageSize, Integer showStatus);

	/**
	 * ������Ʒ��Ϣ
	 * 
	 * @param product
	 * @param imgList
	 * @param paraList
	 * @param skuList
	 */
	void saveProduct(Product product, List<ProductImage> imgList, List<ProductAttrValue> paraList,
			List<ProductSku> skuList);

	/**
	 * ��ѯ��Ʒ��Ϣ���������ࡢƷ�ơ�ͼƬ�����ԡ����۵�Ԫ�͹��
	 * 
	 * @param pid
	 *            ��Ʒid
	 * @return
	 */
	ProductInfo selectProductDetail(Integer pid);

	/**
	 * �ı����¼�״̬
	 * 
	 * @param pid
	 *            ��Ʒid
	 * @param i
	 *            0��Ϊ�¼ܣ�1��Ϊ�ϼ�
	 */
	void updateShowStatus(Integer pid, int i);

	/**
	 * ɾ����Ʒ��Ϣ
	 * 
	 * @param pId
	 */
	void deleteProduct(Integer pId);

	/**
	 * ǰ̨���ݲ�ѯ������ѯ��Ʒ
	 * 
	 * @param page
	 * @param pageSize
	 * @param query
	 * @return
	 */
	PageBean selectProductByQuery(Integer page, Integer integer, ProductQuery query);

	/**
	 * �޸���Ʒ��Ϣ
	 * 
	 * @param product
	 * @param imgList
	 * @param paraList
	 * @param skuList
	 */
	void updateProduct(Product product, List<ProductImage> imgList, List<ProductAttrValue> paraList,
			List<ProductSku> skuList);

	/**
	 * ���潻�׿���
	 * 
	 * @param snapshot
	 */
	void saveSnapshot(ProductSnapshot snapshot);

	/**
	 * ��ѯ���׿���
	 * 
	 * @param id
	 * @return
	 */
	ProductSnapshot selectSnapshot(Integer id);

	/**
	 * ��solr��������Ʒ��Ϣ
	 * @param queryString ����ѯ����
	 * @param para	ɸѡ����
	 * @param price �۸�
	 * @param sort  ����
	 * @param page  ��ǰҳ��
	 * @return
	 * @throws Exception
	 */
	SearchResult queryProduct(String queryString, String para, String price, 
			String sort, Integer page) throws Exception;

	/**
	 * ��ѯ������Ʒ
	 * @return
	 */
	List<Product> selectExpensiveProduct();

	/**
	 * ��ѯ��������Ʒ
	 * @return
	 */
	List<Product> selectNewProduct();

	/**
	 * �������ѯ��Ʒ
	 * @param integer
	 * @return
	 */
	List<Product> selectByCsid(Integer integer);
}
