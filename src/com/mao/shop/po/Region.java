package com.mao.shop.po;

public class Region {
    private Short regionId;

    private Short parentId;

    private String regionName;

    private Short regionType;

    public Short getRegionId() {
        return regionId;
    }

    public void setRegionId(Short regionId) {
        this.regionId = regionId;
    }

    public Short getParentId() {
        return parentId;
    }

    public void setParentId(Short parentId) {
        this.parentId = parentId;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public Short getRegionType() {
        return regionType;
    }

    public void setRegionType(Short regionType) {
        this.regionType = regionType;
    }
}