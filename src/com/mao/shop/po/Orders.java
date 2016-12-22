package com.mao.shop.po;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class Orders {
	
	/**
	 * 未付款
	 */
	public static Short NO_PAY = 0;
	/**
	 * 已付款未发货
	 */
	public static Short PAY = 1;
	/**
	 * 已发货未收货
	 */
	public static Short PAY_SEND = 2;
	/**
	 * 已收货未评价
	 */
	public static Short RECEIPT_NODEVAL = 3;
	/**
	 * 评价完成
	 */
	public static Short COMPLEPE = 4;
	
    private Integer oid;

    private String orderSn;

    private Integer custId;

    private BigDecimal productPrice;

    private BigDecimal freightFee;

    private BigDecimal totalPrice;

    private String custRemark;

    private Short status;

    private Short isDelete;

    private Date payAt;

    private Date placeAt;
    
    private Date deliverAt;
    
    private Date dealAt;
    //new add
    private List<OrderItem> itemList;

    public Integer getOid() {
        return oid;
    }

    public void setOid(Integer oid) {
        this.oid = oid;
    }

    public String getOrderSn() {
        return orderSn;
    }

    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn;
    }

    public Integer getCustId() {
        return custId;
    }

    public void setCustId(Integer custId) {
        this.custId = custId;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public BigDecimal getFreightFee() {
        return freightFee;
    }

    public void setFreightFee(BigDecimal freightFee) {
        this.freightFee = freightFee;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getCustRemark() {
        return custRemark;
    }

    public void setCustRemark(String custRemark) {
        this.custRemark = custRemark;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public Short getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Short isDelete) {
        this.isDelete = isDelete;
    }

    public Date getPayAt() {
        return payAt;
    }

    public void setPayAt(Date payAt) {
        this.payAt = payAt;
    }

    public Date getPlaceAt() {
        return placeAt;
    }

    public void setPlaceAt(Date placeAt) {
        this.placeAt = placeAt;
    }

	public List<OrderItem> getItemList() {
		return itemList;
	}

	public void setItemList(List<OrderItem> itemList) {
		this.itemList = itemList;
	}

	public Date getDeliverAt() {
		return deliverAt;
	}

	public void setDeliverAt(Date deliverAt) {
		this.deliverAt = deliverAt;
	}

	public Date getDealAt() {
		return dealAt;
	}

	public void setDealAt(Date dealAt) {
		this.dealAt = dealAt;
	}
}