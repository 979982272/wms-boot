package com.tudou.system.platform.dataBase.entity;

/**
 * 数据库表实体类
 */
public class DataBase {
    // 表名
    private String tableName;
    // 表注释
    private String tableComment;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableComment() {
        return tableComment;
    }

    public void setTableComment(String tableComment) {
        this.tableComment = tableComment;
    }
}
