package com.tudou.system.platform.base.controller;

import com.tudou.system.platform.base.inject.InjectBaseDependency;
import com.tudou.system.platform.base.model.DataSourceRequest;
import com.tudou.system.platform.base.model.DataSourceResult;
import com.tudou.system.platform.base.model.StatusModel;
import com.tudou.system.platform.base.service.IBaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 增删改查控制器
 *
 * @author weihua
 * @create 2017-04-23 9:38
 */
public abstract class BaseCurdController<T> extends BaseController implements InitializingBean {
    public static final Logger logger = LoggerFactory.getLogger(BaseCurdController.class);
    private IBaseService<T, String> baseService;

    public void setBaseService(IBaseService<T, String> baseService) {
        this.baseService = baseService;
    }

    @RequestMapping(value = "loadData")
    @ResponseBody
    public DataSourceResult loadData(@RequestBody DataSourceRequest dataSourceRequest, HttpServletRequest request) {
        DataSourceResult dataSourceResult = new DataSourceResult();
        Type t = this.getClass().getGenericSuperclass();
        String loadClass = null;
        // 获取泛型的实际类型
        if (ParameterizedType.class.isAssignableFrom(t.getClass())) {
            for (Type t1 : ((ParameterizedType) t).getActualTypeArguments()) {
                loadClass = t1.getTypeName();
            }
        }
        try {
            Class c = Class.forName(loadClass);
            dataSourceResult = baseService.loadData(c, dataSourceRequest, request);
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage(), e);
            e.printStackTrace();
        }
        return dataSourceResult;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        InjectBaseDependency.findInjectBaseServiceDependency(this);
        Assert.notNull(this.baseService, "IBaseService没有设置:类名【" + this.getClass() + "】;继承了【BaseController】的类必须拥有一个继承于【IBaseService】的属性并且注解【@BaseResource】!");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}/selectById", method = RequestMethod.GET)
    @ResponseBody
    public T selectById(@PathVariable("id") String id) {
        T t = null;
        try {
            t = baseService.selectById(id);
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage(), e);
            e.printStackTrace();
        }
        return t;
    }

    /**
     * 查询所有数据
     *
     * @return
     */
    @RequestMapping(value = "selectAll", method = RequestMethod.GET)
    @ResponseBody
    public List<T> selectAll() {
        List<T> t = null;
        try {
            t = baseService.selectAll();
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage(), e);
            e.printStackTrace();
        }
        return t;
    }

    /**
     * 通过主键id删除
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}/deleteById", method = RequestMethod.POST)
    @ResponseBody
    public StatusModel deleteById(@PathVariable("id") String id) {
        try {
            baseService.deleteById(id);
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage(), e);
            e.printStackTrace();
            return new StatusModel(false, e.getMessage());
        }
        return new StatusModel(true, "删除成功");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @RequestMapping(value = "/deleteBatchByIds", method = RequestMethod.POST)
    @ResponseBody
    public StatusModel deleteBatchByIds(@RequestParam(value = "ids") String ids) {
        try {
            baseService.deleteBatchByIds(ids.split(","));
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage(), e);
            e.printStackTrace();
            return new StatusModel(false, e.getMessage());
        }
        return new StatusModel(true, "删除成功");
    }

    /**
     * 保存
     *
     * @param t
     * @return
     */
    @RequestMapping(value = "create", method = RequestMethod.POST)
    @ResponseBody
    public StatusModel create(@RequestBody T t) {
        T entity = null;
        Map<String, Object> map = new HashMap<>();
        try {
            entity = baseService.save(t);
            map.put("entity", entity);
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage(), e);
            e.printStackTrace();
            return new StatusModel(false, e.getMessage());
        }
        return new StatusModel(true, "保存成功!", map);
    }

    /**
     * 更新
     *
     * @param t
     * @return
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    @ResponseBody
    public StatusModel update(@RequestBody T t) {
        try {
            baseService.update(t);
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage(), e);
            e.printStackTrace();
            return new StatusModel(false, e.getMessage());
        }
        return new StatusModel(true, "更新成功!");
    }

    /**
     * 编辑页面与创建页面
     *
     * @param model
     * @param id
     * @return
     */
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String editView(Model model, @RequestParam(value = "id", required = false) String id) {
        model.addAttribute("id", id);
        return this.view("edit");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}/getDataInfoById", method = RequestMethod.POST)
    @ResponseBody
    public StatusModel getDataInfoById(@PathVariable(value = "id") String id) {
        StatusModel statusModel = null;
        try {
            T t = baseService.selectById(id);
            Map<String, Object> map = new HashMap<>();
            map.put("modelData", t);
            statusModel = new StatusModel(true, map);
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage(), e);
            e.printStackTrace();
            statusModel = new StatusModel(false, e.getMessage());
        }
        return statusModel;
    }

}
