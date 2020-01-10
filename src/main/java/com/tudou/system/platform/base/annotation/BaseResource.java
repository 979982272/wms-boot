package com.tudou.system.platform.base.annotation;

import java.lang.annotation.*;

/**
 * Created by Administrator on 2017/2/12 0012.
 */
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface BaseResource {
}
