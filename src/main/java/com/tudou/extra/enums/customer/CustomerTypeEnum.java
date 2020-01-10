package com.tudou.extra.enums.customer;

/**
 * 客户类型
 */
public enum CustomerTypeEnum {
    AFTERMARKET("10", "售后"),
    MARKET("20", "商场"),
    RETAIL("30", "零售");
    private String code;
    private String name;

    CustomerTypeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
