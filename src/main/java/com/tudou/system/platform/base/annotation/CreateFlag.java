package com.tudou.system.platform.base.annotation;

import java.lang.annotation.*;

/**
 * 标识创建，创建的时候记录创建人和创建时间。
 * 如果字段不相同修改默认值
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CreateFlag {
    String createTime() default "createTime";

    String createEmp() default "createEmp";

    String createEmpId() default "createEmpId";
}
