package com.tudou.crm.storage.inStorage.mapper;

import com.tudou.crm.storage.inStorage.entity.InStorageWork;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface InStorageWorkMapper extends Mapper<InStorageWork> {
    /**
     * 通过id查询入库单
     *
     * @param id
     * @return
     */
    InStorageWork findInStorageWorkById(String id);

    /**
     * 修改入库单状态
     *
     * @param id
     * @param receivingCode
     * @throws Exception
     */
    void updateInStorageWorkStatusById(@Param("id") String id, @Param("receivingCode") Integer receivingCode) throws Exception;
}