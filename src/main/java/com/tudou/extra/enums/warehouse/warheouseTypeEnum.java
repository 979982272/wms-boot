package com.tudou.extra.enums.warehouse;

/**
 * 仓库类型枚举
 *
 * @author weihua
 * @create 2017-05-17 10:30
 */
public enum warheouseTypeEnum {
    AFTERMARKET("10", "售后仓"),
    MATERIAL("20", "物料仓"),
    QUANTITY("30", "限量仓"),
    RETAIL("40","零售仓");
    private String code;
    private String name;

    warheouseTypeEnum(String code, String name) {
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
