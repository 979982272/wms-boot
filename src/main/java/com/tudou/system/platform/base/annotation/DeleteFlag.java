package com.tudou.system.platform.base.annotation;

import java.lang.annotation.*;

/**
 * 标识删除，删除的时候记录删除人和删除时间。
 * 如果字段不相同修改默认值
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DeleteFlag {
    String deleteTime() default "deleteTime";

    String deleteEmp() default "deleteEmp";

    String deleteEmpId() default "deleteEmpId";
}
