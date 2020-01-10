package com.tudou.extra.enums.purchase;

/**
 * 采购订单状态枚举
 *
 * @author weihua
 * @create 2017-05-20 12:11
 */
public enum PurchaseOrderStatusEnum {
    CREATE(10, "制单"),
    AUDIT(20, "已审核"),
    PARTRECEIVING(30, "部分入库"),
    SUCCESSRECEIVING(40, "完全入库");
    private Integer code;
    private String name;

    PurchaseOrderStatusEnum(Integer code, String name) {
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
