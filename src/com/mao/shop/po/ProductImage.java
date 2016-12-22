package com.mao.shop.po;

public class ProductImage {
    private Integer pimId;

    private Integer productId;

    private String imageName;

    private String filepath;

    private Byte sortOrder;

    public Integer getPimId() {
        return pimId;
    }

    public void setPimId(Integer pimId) {
        this.pimId = pimId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public Byte getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Byte sortOrder) {
        this.sortOrder = sortOrder;
    }
}