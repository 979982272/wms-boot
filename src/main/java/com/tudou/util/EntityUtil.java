package com.tudou.util;

import com.tudou.extra.system.AuthInfo;
import com.tudou.extra.system.UserInfoUtil;
import com.tudou.system.platform.base.annotation.AuditFlag;
import com.tudou.system.platform.base.annotation.CreateFlag;
import com.tudou.system.platform.base.annotation.ModifyFlag;
import com.tudou.system.platform.base.annotation.NotEmpty;
import com.tudou.system.platform.base.model.DataSourceRequest;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.ReflectionUtils;
import tk.mybatis.mapper.entity.Example;

import javax.persistence.Column;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 实体类工具
 *
 * @author weihua
 * @create 2017-05-16 1:27
 */
public final class EntityUtil {
    /**
     * 验证必填
     *
     * @param object
     * @throws Exception
     */
    public static void validRequired(Object object) throws Exception {
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Annotation[] annotations = field.getAnnotations();
            for (Annotation annotation : annotations) {
                if (annotation instanceof NotEmpty) {
                    Object filedValue = ReflectionUtils.getField(field, object);
                    // 由于实体类的生成有mybatis提供，暂时没有定制实体类的生成。所以再抛出错误字段的时候有file名称代替
                    if (null == filedValue) {
                        throw new Exception(field.getName() + "不能为空!");
                    }
                    if (StringUtils.isEmpty(filedValue.toString())) {
                        throw new Exception(field.getName() + "不能为空!");
                    }
                }
            }
        }
    }

    /**
     * 设置对象的创建信息
     *
     * @param o
     * @throws Exception
     */
    public static void setEntityCreateInfo(Object o) throws Exception {
        Class c = o.getClass();
        Annotation[] annotations = c.getAnnotations();
        boolean createFlag = false;
        CreateFlag createFlagInfo = null;
        for (Annotation annotation : annotations) {
            if (annotation instanceof CreateFlag) {
                createFlag = true;
                createFlagInfo = (CreateFlag) annotation;
            }
        }
        if (createFlag) {
            AuthInfo authInfo = UserInfoUtil.getCurrentAuthInfo();
            Field createTimeField = c.getDeclaredField(createFlagInfo.createTime());
            Field createEmpIdField = c.getDeclaredField(createFlagInfo.createEmpId());
            Field createEmpField = c.getDeclaredField(createFlagInfo.createEmp());
            createTimeField.setAccessible(true);
            createEmpIdField.setAccessible(true);
            createEmpField.setAccessible(true);
            createTimeField.set(o, new Date());
            createEmpIdField.set(o, authInfo.getEmpId());
            createEmpField.set(o, authInfo.getEmpName());
        }
    }

    /**
     * 设置对象的修改信息
     *
     * @param o
     * @throws Exception
     */
    public static void setEntityModifyInfo(Object o) throws Exception {
        Class c = o.getClass();
        Annotation[] annotations = c.getAnnotations();
        boolean flag = false;
        ModifyFlag modifyFlag = null;
        for (Annotation annotation : annotations) {
            if (annotation instanceof ModifyFlag) {
                flag = true;
                modifyFlag = (ModifyFlag) annotation;
            }
        }
        if (flag) {
            AuthInfo authInfo = UserInfoUtil.getCurrentAuthInfo();
            Field createTimeField = c.getDeclaredField(modifyFlag.modifyTime());
            Field createEmpIdField = c.getDeclaredField(modifyFlag.modifyEmpId());
            Field createEmpField = c.getDeclaredField(modifyFlag.modifyEmp());
            createTimeField.setAccessible(true);
            createEmpIdField.setAccessible(true);
            createEmpField.setAccessible(true);
            createTimeField.set(o, new Date());
            createEmpIdField.set(o, authInfo.getEmpId());
            createEmpField.set(o, authInfo.getEmpName());
        }
    }

    /**
     * 设置对象的删除信息
     *
     * @param o
     * @throws Exception
     */
    public static void setEntityDeleteInfo(Object o) throws Exception {
        Class c = o.getClass();
        Annotation[] annotations = c.getAnnotations();
        boolean flag = false;
        ModifyFlag modifyFlag = null;
        for (Annotation annotation : annotations) {
            if (annotation instanceof ModifyFlag) {
                flag = true;
                modifyFlag = (ModifyFlag) annotation;
            }
        }
        if (flag) {
            AuthInfo authInfo = UserInfoUtil.getCurrentAuthInfo();
            Field createTimeField = c.getDeclaredField(modifyFlag.modifyTime());
            Field createEmpIdField = c.getDeclaredField(modifyFlag.modifyEmpId());
            Field createEmpField = c.getDeclaredField(modifyFlag.modifyEmp());
            createTimeField.setAccessible(true);
            createEmpIdField.setAccessible(true);
            createEmpField.setAccessible(true);
            createTimeField.set(o, new Date());
            createEmpIdField.set(o, authInfo.getEmpId());
            createEmpField.set(o, authInfo.getEmpName());
        }
    }

    /**
     * 设置审核时间
     * @param o
     * @throws Exception
     */
    public static void setEntityAuditInfo(Object o) throws Exception {
        Class c = o.getClass();
        Annotation[] annotations = c.getAnnotations();
        boolean flag = false;
        AuditFlag auditFlag = null;
        for (Annotation annotation : annotations) {
            if (annotation instanceof AuditFlag) {
                flag = true;
                auditFlag = (AuditFlag) annotation;
            }
        }
        if (flag) {
            AuthInfo authInfo = UserInfoUtil.getCurrentAuthInfo();
            Field timeField = c.getDeclaredField(auditFlag.auditTime());
            Field empIdField = c.getDeclaredField(auditFlag.auditEmpId());
            Field tmpField = c.getDeclaredField(auditFlag.auditEmp());
            timeField.setAccessible(true);
            empIdField.setAccessible(true);
            tmpField.setAccessible(true);
            timeField.set(o, new Date());
            empIdField.set(o, authInfo.getEmpId());
            tmpField.set(o, authInfo.getEmpName());
        }
    }

    /**
     * 通过kendoui的请求来构建查询语句
     *
     * @param dataSourceRequest
     * @param example
     */
    public static void buidSqlByRequest(DataSourceRequest dataSourceRequest, Example example) {
        DataSourceRequest.FilterDescriptor filterDescriptor = dataSourceRequest.getFilter();
        if (null != filterDescriptor) {
            String operator = null;
            Example.Criteria criteria = example.createCriteria();
            String field = null;
            String value = null;
            Map<String, String> filedMap = transEntiryFiled(example.getEntityClass());
            criteria.andCondition("(server_flag is null or  server_flag !=1 )");
            for (DataSourceRequest.FilterDescriptor filter : filterDescriptor.getFilters()) {
                if ("contains".equals(filter.getOperator())) {
                    operator = "like";
                }
                if ("neq".equals(filter.getOperator())) {
                    operator = "not like";
                }
                if ("eq".equals(filter.getOperator())) {
                    operator = "=";
                }
                value = filter.getValue() != null ? filter.getValue().toString() : "";
                field = filter.getField();
                if (filedMap.containsKey(field)) {
                    field = filedMap.get(field);
                }
                if (("like".equals(operator) || "not like".equals(operator)) && org.apache.commons.lang.StringUtils.isNotEmpty(value)) {
                    value = "%" + value + "%";
                }
                criteria.andCondition(field + " " + operator, value);
            }
        }
    }

    /**
     * 通过dataSourceRequest构建sql查询语句
     *
     * @param dataSourceRequest
     * @return
     */
    public static String buidSqlByRequest(DataSourceRequest dataSourceRequest, Class entityClass) {
        StringBuilder sql = new StringBuilder();
        sql.append(" and (server_flag is null or  server_flag !=1 ) ");
        DataSourceRequest.FilterDescriptor filterDescriptor = dataSourceRequest.getFilter();
        if (null != filterDescriptor) {
            String operator = null;
            String field = null;
            String value = null;
            Map<String, String> filedMap = transEntiryFiled(entityClass);
            for (DataSourceRequest.FilterDescriptor filter : filterDescriptor.getFilters()) {
                if ("contains".equals(filter.getOperator())) {
                    operator = "like";
                }
                if ("neq".equals(filter.getOperator())) {
                    operator = "not like";
                }
                if ("eq".equals(filter.getOperator())) {
                    operator = "=";
                }
                value = filter.getValue() != null ? filter.getValue().toString() : "";
                field = filter.getField();
                if (filedMap.containsKey(field)) {
                    field = filedMap.get(field);
                }
                if (("like".equals(operator) || "not like".equals(operator)) && org.apache.commons.lang.StringUtils.isNotEmpty(value)) {
                    value = "'%" + value + "%'";
                }
                sql.append(" and  " + field + " " + operator + " " + value);
            }
        }

        return sql.toString();
    }


    /**
     * 将实体类的字段名称转换为数据库的字段名称
     *
     * @param c
     * @return
     */
    public static Map<String, String> transEntiryFiled(Class c) {
        Map<String, String> map = new HashMap<>();
        Field[] fields = c.getDeclaredFields();
        for (Field field : fields) {
            Annotation[] annotations = field.getDeclaredAnnotations();
            for (Annotation annotation : annotations) {
                if (annotation instanceof Column) {
                    map.put(field.getName(), ((Column) annotation).name());
                }
            }
        }
        return map;
    }
}
