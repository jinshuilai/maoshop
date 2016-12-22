package com.mao.shop.po;

import java.util.List;

public class SearchResult {

	private List<Product> productList;
	//��¼����
	private Long recordCount;
	//��ҳ��
	private int pageCount;
	//��ǰҳ
	private int curPage;
	
	public List<Product> getProductList() {
		return productList;
	}
	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}
	public Long getRecordCount() {
		return recordCount;
	}
	public void setRecordCount(Long recordCount) {
		this.recordCount = recordCount;
	}
	public int getPageCount() {
		pageCount = (int) ((recordCount + 8) / 9);
		return pageCount;
	}
	
	public int getCurPage() {
		return curPage;
	}
	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}
	
}
