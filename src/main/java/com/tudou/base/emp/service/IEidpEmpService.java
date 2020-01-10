package com.tudou.base.emp.service;

import com.tudou.base.emp.entity.EidpEmp;
import com.tudou.system.platform.base.service.IBaseService;

public interface IEidpEmpService extends IBaseService<EidpEmp, String> {

    String getCreateEidpEmpId();

    /**
     * 验证登录
     *
     * @param userName
     * @param password
     * @throws Exception
     */
    void valid(String userName, String password) throws Exception;
}
