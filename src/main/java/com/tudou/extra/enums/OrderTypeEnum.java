package com.tudou.extra.enums;

/**
 * 订单类型
 *
 * @author weihua
 * @create 2017-05-18 17:21
 */
public enum OrderTypeEnum {
    STOCKBEGIN("SB", "库存期初"),
    PURCHASEAPPLY("PA", "采购申请"),
    PURCHASEORDER("PO","采购单"),
    INSTORAGEWORK("IS","入库单"),
    OUTSTORAGEWORK("OS","出库单"),
    SALESORDER("SO","销售单");
    private String code;
    private String name;

    OrderTypeEnum(String code, String name) {
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
