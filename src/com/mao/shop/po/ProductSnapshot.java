package com.mao.shop.po;

import java.math.BigDecimal;

public class ProductSnapshot {
    private Integer snapId;

    private Integer productId;

    private String productName;
    
    private String productImg;

    private String spec;

    private BigDecimal buyPrice;

    private String paramter;

    private String desctext;

    public Integer getSnapId() {
        return snapId;
    }

    public void setSnapId(Integer snapId) {
        this.snapId = snapId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public BigDecimal getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(BigDecimal buyPrice) {
        this.buyPrice = buyPrice;
    }

    public String getParamter() {
        return paramter;
    }

    public void setParamter(String paramter) {
        this.paramter = paramter;
    }

    public String getDesctext() {
        return desctext;
    }

    public void setDesctext(String desctext) {
        this.desctext = desctext;
    }

	public String getProductImg() {
		return productImg;
	}

	public void setProductImg(String productImg) {
		this.productImg = productImg;
	}
}