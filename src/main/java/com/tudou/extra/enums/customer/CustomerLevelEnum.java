package com.tudou.extra.enums.customer;

/**
 * 客户级别
 */
public enum CustomerLevelEnum {
    VIP("10", "VIP客户"),
    IMPORTANT("20", "重要客户"),
    ORDINARY("30", "一般客户");
    private String code;
    private String name;

    CustomerLevelEnum(String code, String name) {
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
