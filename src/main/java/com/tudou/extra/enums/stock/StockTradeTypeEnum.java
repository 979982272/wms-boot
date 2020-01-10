package com.tudou.extra.enums.stock;

/**
 * 库存交易类型
 *
 * @author weihua
 * @create 2017-05-17 23:18
 */
public enum StockTradeTypeEnum {
    STOCKBEGIN("10", "库存期初"),
    INSTOCK("20", "入库"),
    OUTSTOCK("30","出库");
    private String code;
    private String name;

    StockTradeTypeEnum(String code, String name) {
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
