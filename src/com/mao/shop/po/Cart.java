package com.mao.shop.po;

import com.google.gson.annotations.Expose;

public class Cart {

	@Expose
	private Integer skuId;
	@Expose
	private Integer quantity;
	private ProductSku sku;
	public Integer getSkuId() {
		return skuId;
	}
	public void setSkuId(Integer skuId) {
		this.skuId = skuId;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public ProductSku getSku() {
		return sku;
	}
	public void setSku(ProductSku sku) {
		this.sku = sku;
	}
	
}
