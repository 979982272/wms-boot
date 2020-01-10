package com.tudou.extra.enums.purchase;

/**
 * 采购申请单状态
 *
 * @author weihua
 * @create 2017-05-19 23:28
 */
public enum PurchaseApplyStatusEnum {
    CREATE(10, "制单"),
    AUDIT(20, "已审核"),
    PARTPUSH(30, "部分下推"),
    SUCCESSPUSH(40,"完全下推");
    private Integer code;
    private String name;

    PurchaseApplyStatusEnum(Integer code, String name) {
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
