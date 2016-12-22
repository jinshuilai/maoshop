package com.mao.shop.po;

public class CustShipaddr extends ShippingAddress {
    private Integer shipid;

    private Integer customerId;

    private Boolean isDefault;

    private String remark;
    
    //new add
    private String province;
    private String city;
    private String district;

    public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public Integer getShipid() {
        return shipid;
    }

    public void setShipid(Integer shipid) {
        this.shipid = shipid;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Boolean getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Boolean isDefault) {
        this.isDefault = isDefault;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}