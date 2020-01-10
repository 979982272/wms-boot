package com.tudou.extra.enums;

/**
 * 性别枚举
 *
 * @author weihua
 * @create 2017-05-13 10:13
 */
public enum SexEnum {
    ;
    private String code;
    private String name;

    SexEnum(String code, String name) {
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
