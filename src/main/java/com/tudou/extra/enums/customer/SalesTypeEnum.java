package com.tudou.extra.enums.customer;

/**
 * 销售类型
 */
public enum SalesTypeEnum {
    DIRECT("10","直销"),
    ENTRUST("20","委托代销"),
    RETAIL("30","零售");
    private String code;
    private String name;

    SalesTypeEnum(String code, String name) {
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
