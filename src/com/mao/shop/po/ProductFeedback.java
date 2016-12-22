package com.mao.shop.po;

import java.util.Date;

public class ProductFeedback {
    private Integer feedId;

    private Integer custId;

    private Integer itemId;

    private Short fbLevel;

    private String fbImg;

    private Byte anonymous;

    private Date createdAt;

    private String content;
    
    //new add
    private String sku;
    private String custName;
    private Integer productId;

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public Integer getFeedId() {
        return feedId;
    }

    public void setFeedId(Integer feedId) {
        this.feedId = feedId;
    }

    public Integer getCustId() {
        return custId;
    }

    public void setCustId(Integer custId) {
        this.custId = custId;
    }

    public Short getFbLevel() {
        return fbLevel;
    }

    public void setFbLevel(Short fbLevel) {
        this.fbLevel = fbLevel;
    }

    public String getFbImg() {
        return fbImg;
    }

    public void setFbImg(String fbImg) {
        this.fbImg = fbImg;
    }

    public Byte getAnonymous() {
        return anonymous;
    }

    public void setAnonymous(Byte anonymous) {
        this.anonymous = anonymous;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}
}