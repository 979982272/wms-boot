package com.tudou.crm.storage.outStorage.mapper;

import com.tudou.crm.storage.outStorage.entity.OutStorageWork;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface OutStorageWorkMapper extends Mapper<OutStorageWork> {
    /**
     * 通过出库单编码查询
     *
     * @param id
     * @return
     */
    OutStorageWork findOutStorageWorkById(String id);

    /**
     * 修改状态
     *
     * @param id
     * @param deliveryCode
     */
    void updateOutStorageWorkStatusById(@Param("id") String id, @Param("deliveryCode") Integer deliveryCode);
}