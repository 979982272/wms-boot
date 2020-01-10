package com.tudou.base.vendor.service.impl;

import com.tudou.base.vendor.entity.Vendor;
import com.tudou.base.vendor.mapper.VendorMapper;
import com.tudou.base.vendor.service.IVendorService;
import com.tudou.system.platform.base.annotation.BaseResource;
import com.tudou.system.platform.base.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Transactional(rollbackFor = Exception.class)
@Service("vendorService")
public class VendorServiceImpl extends BaseServiceImpl<Vendor,String> implements IVendorService{

    @Resource
    @BaseResource
    private VendorMapper vendorMapper;

}