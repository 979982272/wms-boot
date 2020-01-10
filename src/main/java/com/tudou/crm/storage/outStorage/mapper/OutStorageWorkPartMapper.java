package com.tudou.crm.storage.outStorage.mapper;

import com.tudou.crm.storage.outStorage.entity.OutStorageWorkPart;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface OutStorageWorkPartMapper extends Mapper<OutStorageWorkPart> {
    /**
     * 通过出库单查询明细
     *
     * @param id
     * @return
     */
    List<OutStorageWorkPart> findOutStorageWorkPartByOutStorageWork(String id);
}