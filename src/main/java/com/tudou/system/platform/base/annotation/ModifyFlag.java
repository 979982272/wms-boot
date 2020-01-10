package com.tudou.system.platform.base.annotation;

import java.lang.annotation.*;

/**
 * 标识修改，创建的时候记录修改人和修改时间。
 * 如果字段不相同修改默认值
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ModifyFlag {
    String modifyTime() default "modifyTime";

    String modifyEmp() default "modifyEmp";

    String modifyEmpId() default "modifyEmpId";
}
