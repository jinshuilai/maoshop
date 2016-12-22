package com.mao.shop.po;

public class ProductAttrValue {
    private Integer pavId;

    private Integer productId;

    private Integer attrId;

    private String attrValue;
    //new add
    private String featureName;

    public Integer getPavId() {
        return pavId;
    }

    public void setPavId(Integer pavId) {
        this.pavId = pavId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getAttrId() {
        return attrId;
    }

    public void setAttrId(Integer attrId) {
        this.attrId = attrId;
    }

    public String getAttrValue() {
        return attrValue;
    }

    public void setAttrValue(String attrValue) {
        this.attrValue = attrValue;
    }

	public String getFeatureName() {
		return featureName;
	}

	public void setFeatureName(String featureName) {
		this.featureName = featureName;
	}
}