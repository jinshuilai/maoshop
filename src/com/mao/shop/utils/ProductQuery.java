package com.mao.shop.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

public class ProductQuery {

	private String price;
	private Integer bid;
	private List<String> paraList;
	private Integer cid;
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public Integer getBid() {
		return bid;
	}
	public void setBid(Integer bid) {
		this.bid = bid;
	}
	public List<String> getParaList() {
		return paraList;
	}
	public void setParaList(List<String> paraList) {
		this.paraList = paraList;
	}
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	
	public Map<String, Object> getQueryMap() {
		Map<String, Object> map = new HashMap<String,Object>();
		if (StringUtils.isNotBlank(this.price)) {
			String[] prices = this.price.split("-");
			map.put("minPrice", prices[0]);
			map.put("maxPrice", prices[1]);
		}
		map.put("brandId", this.bid);
		map.put("cid", this.cid);
		map.put("paraList", this.paraList);

		return map;
	}
	
	
}
