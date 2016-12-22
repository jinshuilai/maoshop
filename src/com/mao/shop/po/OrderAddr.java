package com.mao.shop.po;

public class OrderAddr extends ShippingAddress {
    private Integer oaddId;

    private Integer orderId;

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

	public Integer getOaddId() {
        return oaddId;
    }

    public void setOaddId(Integer oaddId) {
        this.oaddId = oaddId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }


	public void copyShipAddr(CustShipaddr shipaddr) {
		this.consignee = shipaddr.getConsignee();
		this.phone = shipaddr.getPhone();
		this.postcode = shipaddr.getPostcode();
		this.address = shipaddr.getAddress();
		this.provinceId = shipaddr.getProvinceId();
		this.cityId = shipaddr.getCityId();
		this.districtId = shipaddr.getDistrictId();
		
	}
}