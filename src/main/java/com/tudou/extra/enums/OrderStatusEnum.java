package com.tudou.extra.enums;

/**
 * 订单状态
 *
 * @author weihua
 * @create 2017-05-17 18:32
 */
public enum OrderStatusEnum {
    CREATE(10, "制单"),
    AUDIT(20, "已审核"),
    PARTPUSHDOWN(30, "部分下推"),
    PUSHDOWN(40, "已下推");

    private Integer code;
    private String name;

    OrderStatusEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
