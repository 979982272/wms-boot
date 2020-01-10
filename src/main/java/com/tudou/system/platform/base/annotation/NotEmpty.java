package com.tudou.system.platform.base.annotation;

import java.lang.annotation.*;

/**
 * 不能为空，在保存的时候会进行验证
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NotEmpty {
}
