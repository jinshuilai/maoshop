package com.mao.shop.po;

import java.util.List;

public class Category {
    private Integer id;

    private String name;

    private String icon;
    
    private List<CategorySecond> csList;

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

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

	public List<CategorySecond> getCsList() {
		return csList;
	}

	public void setCsList(List<CategorySecond> csList) {
		this.csList = csList;
	}
}