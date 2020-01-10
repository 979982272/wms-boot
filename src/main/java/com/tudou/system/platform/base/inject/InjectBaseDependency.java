package com.tudou.system.platform.base.inject;/**
 * Created by Administrator on 2017/2/16 0016.
 */

import com.tudou.system.platform.base.annotation.BaseResource;
import com.tudou.system.platform.base.controller.BaseCurdController;
import com.tudou.system.platform.base.service.IBaseService;
import com.tudou.system.platform.base.service.impl.BaseServiceImpl;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.util.ReflectionUtils;
import tk.mybatis.mapper.common.Mapper;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * 注解基础依赖工具类
 *
 * @author wwh
 * @create 2017-02-16 21:58
 */
public class InjectBaseDependency {

    /**
     * 判断服务注入
     *
     * @param baseController
     */
    public static void findInjectBaseServiceDependency(BaseCurdController<?> baseController) {
        // InitializingBean 的实现类，在spring容器初始化完成之后将注解资源注入
        Field[] fields = baseController.getClass().getDeclaredFields();
        List baseResources = new ArrayList();
        for (Field field : fields) {
            field.setAccessible(true);
            Annotation[] annotations = field.getDeclaredAnnotations();
            for (Annotation annotation : annotations) {
                if (annotation instanceof BaseResource) {
                    Object o = ReflectionUtils.getField(field, baseController);
                    baseResources.add(o);
                }
            }
        }
        if (CollectionUtils.isNotEmpty(baseResources)) {
            if (baseResources.size() > 1) {
                throw new IllegalArgumentException("IBaseService没有设置:类名【" + baseController.getClass() + "】;继承了【BaseController】的类只能拥有一个继承于【IBaseService】并且注解【@BaseResource】的属性!");
            }
            // 这里暂时没有去判断添加了BaseResource注解的属性类型
            baseController.setBaseService((IBaseService) baseResources.get(0));
        }

    }

    /**
     * 判断mybatis注入
     *
     * @param baseService
     */
    public static void findInjectBaseMapperDependency(BaseServiceImpl<?, ?> baseService) {
        // InitializingBean 的实现类，在spring容器初始化完成之后将注解资源注入
        Field[] fields = baseService.getClass().getDeclaredFields();
        List baseResources = new ArrayList();
        for (Field field : fields) {
            field.setAccessible(true);
            Annotation[] annotations = field.getDeclaredAnnotations();
            for (Annotation annotation : annotations) {
                if (annotation instanceof BaseResource) {
                    Object o = ReflectionUtils.getField(field, baseService);
                    baseResources.add(o);
                }
            }
        }
        if (CollectionUtils.isNotEmpty(baseResources)) {
            if (baseResources.size() > 1) {
                throw new IllegalArgumentException("Mapper没有设置:类名【" + baseService.getClass() + "】;继承了【IBaseService】的类只能拥有一个继承于【Mapper】并且注解【@BaseResource】的属性!");
            }
            // 这里暂时没有去判断添加了BaseResource注解的属性类型
            baseService.setMapper((Mapper) baseResources.get(0));
        }

    }
}
