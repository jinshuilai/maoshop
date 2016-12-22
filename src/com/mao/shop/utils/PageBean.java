package com.mao.shop.utils;

import java.util.List;

public class PageBean {

	private int pageSize = 5;//ÿҳ��ʾ������
	private int pageNum = 1;//��ǰ�ڵڼ�ҳ
	private int totalPage;//��ҳ��
	private int begin = 0;//mysql
	private int totalCount = 0;//�ܼ�¼��
	private List<?> dataList;//��ǰҳ������
	//���ҳ��Ч����Ҫ�����ֵ
	private int startNum;//�Ӷ���ҳ��ʼ
	private int endNum;//������ҳ��
	
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public int getTotalPage() {
		totalPage = (totalCount + pageSize - 1)/pageSize;
		return totalPage;
	}
	
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public List<?> getDataList() {
		return dataList;
	}
	public void setDataList(List<?> dataList) {
		this.dataList = dataList;
	}
	public int getStartNum() {
		if (pageNum < 4) {
			startNum = 1;
			endNum = totalPage > 5 ? 5:totalPage;
		}else if (pageNum > (totalPage - 2)) {
			endNum = totalPage;
			startNum = totalPage > 5 ? totalPage-4 : 1;
		}else {
			startNum = pageNum - 2;
			endNum = pageNum + 2;
		}
		return startNum;
	}
	
	public int getEndNum() {
		return endNum;
	}
	
	public int getBegin() {
		begin = (pageNum - 1) * pageSize;
		return begin;
	}
	
	

}
