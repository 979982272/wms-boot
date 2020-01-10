package com.tudou.base.vendor.service;

import com.tudou.base.vendor.entity.VendorGoods;
import com.tudou.system.platform.base.service.IBaseService;

import java.math.BigDecimal;

public interface IVendorGoodsService extends IBaseService<VendorGoods, String> {

    /**
     * 保存供应商
     *
     * @param goodsIds
     * @param prices
     * @param vendorId
     * @throws Exception
     */
    void saveVendorGoods(String[] goodsIds, BigDecimal[] prices, String vendorId) throws Exception;

}
