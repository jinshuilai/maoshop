package com.mao.shop.po;

import java.math.BigDecimal;
import java.util.List;

public class ProductSku {
    private Integer skuId;

    private Integer productId;

    private BigDecimal marketPrice;

    private BigDecimal shopPrice;

    private Integer stock;

    private String skuName;
    
    //new add
    private List<ProductSpec> specList;
    private Product product;

    public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getSkuId() {
        return skuId;
    }

    public void setSkuId(Integer skuId) {
        this.skuId = skuId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public BigDecimal getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(BigDecimal marketPrice) {
        this.marketPrice = marketPrice;
    }

    public BigDecimal getShopPrice() {
        return shopPrice;
    }

    public void setShopPrice(BigDecimal shopPrice) {
        this.shopPrice = shopPrice;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getSkuName() {
        return skuName;
    }

    public void setSkuName(String skuName) {
        this.skuName = skuName;
    }

	public List<ProductSpec> getSpecList() {
		return specList;
	}

	public void setSpecList(List<ProductSpec> specList) {
		this.specList = specList;
	}
}