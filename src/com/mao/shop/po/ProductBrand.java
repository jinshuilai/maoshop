package com.mao.shop.po;

public class ProductBrand {
    private Integer id;

    private String name;

    private String logoPath;

    private String sortOrder;

    private String website;

    private String desct;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogoPath() {
        return logoPath;
    }

    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath;
    }

    public String getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getDesct() {
        return desct;
    }

    public void setDesct(String desc) {
        this.desct = desc;
    }
}