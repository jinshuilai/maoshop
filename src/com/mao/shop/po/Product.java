package com.mao.shop.po;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class Product {
    private Integer proId;

    private Integer catId;

    private Integer brandId;

    private String pname;

    private String tag;

    private String shortDesc;

    private Byte showStatus;

    private Byte isHot;

    private Date onSaleAt;

    private Date updatedAt;

    private String desctext;
    
    //new add
    private String cateName;

	private String brandName;
    
    private String imgs;
    
    private BigDecimal price;
    
    private List<String> attrList;
    
    public String getCateName() {
		return cateName;
	}

	public void setCateName(String cateName) {
		this.cateName = cateName;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getImgs() {
		return imgs;
	}

	public void setImgs(String imgs) {
		this.imgs = imgs;
	}

    public Integer getProId() {
        return proId;
    }

    public void setProId(Integer proId) {
        this.proId = proId;
    }

    public Integer getCatId() {
        return catId;
    }

    public void setCatId(Integer catId) {
        this.catId = catId;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public Byte getShowStatus() {
        return showStatus;
    }

    public void setShowStatus(Byte showStatus) {
        this.showStatus = showStatus;
    }

    public Byte getIsHot() {
        return isHot;
    }

    public void setIsHot(Byte isHot) {
        this.isHot = isHot;
    }

    public Date getOnSaleAt() {
        return onSaleAt;
    }

    public void setOnSaleAt(Date onSaleAt) {
        this.onSaleAt = onSaleAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getDesctext() {
        return desctext;
    }

    public void setDesctext(String desctext) {
        this.desctext = desctext;
    }

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public List<String> getAttrList() {
		return attrList;
	}

	public void setAttrList(List<String> attrList) {
		this.attrList = attrList;
	}

	


}