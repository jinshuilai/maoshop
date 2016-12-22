package com.mao.shop.po;

import java.util.List;

public class SearchResult {

	private List<Product> productList;
	//记录条数
	private Long recordCount;
	//总页数
	private int pageCount;
	//当前页
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
