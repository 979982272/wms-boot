package com.tudou.system.platform.dataBase.mapper;

import com.tudou.system.platform.base.model.DataSourceRequest;

/**
 * 数据库动态查询
 *
 * @author weihua
 * @create 2017-04-25 23:06
 */
public class DataBaseMapperDynmic {
    public String showTablesProvider(DataSourceRequest dataSourceRequest) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT TABLE_NAME,TABLE_COMMENT FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'base' ");
        DataSourceRequest.FilterDescriptor filterDescriptor = dataSourceRequest.getFilter();
        String field = null;
        int page = 0;
        int pageSize = 0;
        String operator = null;
        if (null != filterDescriptor){
            for (DataSourceRequest.FilterDescriptor filter : filterDescriptor.getFilters()) {
                if ("tableName".equals(filter.getField())) {
                    field = "TABLE_NAME";
                }
                if ("tableComment".equals(filter.getField())) {
                    field = "TABLE_COMMENT";
                }
                if ("contains".equals(filter.getOperator())) {
                    operator = "like";
                }
                if ("neq".equals(filter.getOperator())) {
                    operator = "not like";
                }
                if ("eq".equals(filter.getOperator())) {
                    operator = "=";
                }
                sql.append(filterDescriptor.getLogic() + " " + field + " " + operator + " '" + "%" + filter.getValue() + "%' ");
            }
        }

        sql.append(" LIMIT " + (dataSourceRequest.getPage() - 1) * dataSourceRequest.getPageSize() + "," + dataSourceRequest.getPageSize());
        return sql.toString();//"SELECT TABLE_NAME,TABLE_COMMENT FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'base'";
    }
}
