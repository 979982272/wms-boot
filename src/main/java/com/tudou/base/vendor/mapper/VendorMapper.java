package com.tudou.base.vendor.mapper;

import com.tudou.base.vendor.entity.Vendor;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

public interface VendorMapper extends Mapper<Vendor> {
    @Select("select vendor_name from eidp_vendor where id = #{vendorId}")
        //需要修改
        //@Result(column = "vendor_name", property = "vendorName")
    String findVendorNameById(String vendorId);
}