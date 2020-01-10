package com.tudou.system.platform.dataBase.service;

import com.tudou.system.platform.dataBase.entity.DataBase;

import java.util.List;

/**
 * Created by Administrator on 2016/12/24 0024.
 */
public interface IDataBaseService {
    public List<DataBase> findDataBase();

    public void createCode(String[] ids, String[] pack) throws Exception;
}
