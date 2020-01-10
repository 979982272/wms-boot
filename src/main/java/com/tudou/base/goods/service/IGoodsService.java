package com.tudou.base.goods.service;

import com.tudou.base.goods.entity.Goods;
import com.tudou.system.platform.base.model.DataSourceRequest;
import com.tudou.system.platform.base.model.DataSourceResult;
import com.tudou.system.platform.base.service.IBaseService;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface IGoodsService extends IBaseService<Goods, String> {

    /**
     * 获取供应商可选择的产品
     *
     * @param dataSourceRequest
     * @param request
     * @return
     * @throws Exception
     */
    DataSourceResult selectVendorGoods(DataSourceRequest dataSourceRequest, HttpServletRequest request, String vendorId) throws Exception;

    /**
     * 查询所有产品的集合
     *
     * @return
     * @throws Exception
     */
    Map<String, Goods> findAllGoodsMap() throws Exception;
}
