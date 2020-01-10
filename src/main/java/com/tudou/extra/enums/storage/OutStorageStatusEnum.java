package com.tudou.extra.enums.storage;

/**
 * 出库单状态
 *
 * @author weihua
 * @create 2017-05-21 13:53
 */
public enum OutStorageStatusEnum {
    CREATE(10, "制单"),
    AUDIT(20, "已审核"),
    PARTDELIVERY(30,"部分出库"),
    SUCCESSDELIVERY(40,"完全出库");
    private Integer code;
    private String name;

    OutStorageStatusEnum(Integer code, String name) {
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
