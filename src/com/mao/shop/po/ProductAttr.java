package com.mao.shop.po;

public class ProductAttr {
    private Integer paId;

    private Integer cateId;

    private String attrName;

    private Byte attrType;

    private String optionValues;

    private Boolean isSpec;

    private Boolean isSelect;
    
    //new add
    private String cateName;
    private Integer catId;
    private String currentValue;//当前选择的value，回显用

    public Integer getPaId() {
        return paId;
    }

    public void setPaId(Integer paId) {
        this.paId = paId;
    }

    public Integer getCateId() {
        return cateId;
    }

    public void setCateId(Integer cateId) {
        this.cateId = cateId;
    }

    public String getAttrName() {
        return attrName;
    }

    public void setAttrName(String attrName) {
        this.attrName = attrName;
    }

    public Byte getAttrType() {
        return attrType;
    }

    public void setAttrType(Byte attrType) {
        this.attrType = attrType;
    }

    public String getOptionValues() {
        return optionValues;
    }

    public void setOptionValues(String optionValues) {
        this.optionValues = optionValues;
    }

    public Boolean getIsSpec() {
        return isSpec;
    }

    public void setIsSpec(Boolean isSpec) {
        this.isSpec = isSpec;
    }

    public Boolean getIsSelect() {
        return isSelect;
    }

    public void setIsSelect(Boolean isSelect) {
        this.isSelect = isSelect;
    }

	public String getCateName() {
		return cateName;
	}

	public void setCateName(String cateName) {
		this.cateName = cateName;
	}

	public Integer getCatId() {
		return catId;
	}

	public void setCatId(Integer catId) {
		this.catId = catId;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof ProductAttr) {
			ProductAttr pa = (ProductAttr) obj;
			if (pa.getPaId().intValue() == this.paId.intValue()) {
				return true;
			}else {
				return false;
			}
		}
		return super.equals(obj);
	}

	public String getCurrentValue() {
		return currentValue;
	}

	public void setCurrentValue(String currentValue) {
		this.currentValue = currentValue;
	}
}