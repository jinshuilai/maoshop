package com.mao.shop.po;

import java.util.List;

public class ProductInfo extends Product {

	private List<ProductImage> imageList;
	private List<ProductSku> skuList;
	private List<ProductAttrValue> paraList;
	public List<ProductImage> getImageList() {
		return imageList;
	}
	public void setImageList(List<ProductImage> imageList) {
		this.imageList = imageList;
	}
	public List<ProductSku> getSkuList() {
		return skuList;
	}
	public void setSkuList(List<ProductSku> skuList) {
		this.skuList = skuList;
	}
	public List<ProductAttrValue> getParaList() {
		return paraList;
	}
	public void setParaList(List<ProductAttrValue> paraList) {
		this.paraList = paraList;
	}
	
	
}
