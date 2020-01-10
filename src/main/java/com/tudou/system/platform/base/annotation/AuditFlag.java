package com.tudou.system.platform.base.annotation;

import java.lang.annotation.*;

/**
 * 标识创建，创建的时候记录创建人和创建时间。
 * 如果字段不相同修改默认值
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AuditFlag {
    String auditTime() default "auditTime";

    String auditEmp() default "auditEmp";

    String auditEmpId() default "auditEmpId";
}
