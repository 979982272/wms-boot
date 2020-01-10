package com.tudou.base.warehouse.service.impl;

import com.tudou.base.warehouse.entity.Warehouse;
import com.tudou.base.warehouse.mapper.WarehouseMapper;
import com.tudou.base.warehouse.service.IWarehouseService;
import com.tudou.system.platform.base.annotation.BaseResource;
import com.tudou.system.platform.base.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Transactional(rollbackFor = Exception.class)
@Service("warehouseService")
public class WarehouseServiceImpl extends BaseServiceImpl<Warehouse,String> implements IWarehouseService{

    @Resource
    @BaseResource
    private WarehouseMapper warehouseMapper;

}