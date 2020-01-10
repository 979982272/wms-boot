package com.tudou.extra.enums;

/**
 * 是否删除的
 *
 * @author weihua
 * @create 2017-05-15 11:06
 */
public enum ServerFlag {
    NORMAL(0, "正常"),
    Delete(1, "删除");
    private int code;
    private String name;

    ServerFlag(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
