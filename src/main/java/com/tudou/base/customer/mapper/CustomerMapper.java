package com.tudou.base.customer.mapper;

import com.tudou.base.customer.entity.Customer;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface CustomerMapper extends Mapper<Customer> {
    List<Customer> findCustomerByName(@Param("name") String name);

    List<Customer> findCustomerByNameNotId(@Param("name") String name, @Param("id") String id);
}