package com.mao.shop.po;

public class ShippingAddress {

	protected String consignee;

    protected String phone;

    protected String postcode;

    protected String address;

    protected Short provinceId;

    protected Short cityId;

    protected Short districtId;

	public String getConsignee() {
		return consignee;
	}

	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Short getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Short provinceId) {
		this.provinceId = provinceId;
	}

	public Short getCityId() {
		return cityId;
	}

	public void setCityId(Short cityId) {
		this.cityId = cityId;
	}

	public Short getDistrictId() {
		return districtId;
	}

	public void setDistrictId(Short districtId) {
		this.districtId = districtId;
	}
    
    
}
