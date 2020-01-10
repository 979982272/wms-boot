package com.tudou.util;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * 反射工具
 *
 * @author weihua
 * @create 2017-05-17 11:33
 */
public final class ReflectionUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReflectionUtil.class);

    /**
     * 获取类加载器
     *
     * @return
     */
    public static ClassLoader getClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }

    /**
     * 载入类
     *
     * @param className    类名
     * @param isInitializd 是否初始化
     * @return
     */
    public static Class<?> loadClass(String className, boolean isInitializd) {
        Class<?> cls;
        try {
            cls = Class.forName(className, isInitializd, getClassLoader());
        } catch (ClassNotFoundException e) {
            LOGGER.error("类没有找到:" + e);
            throw new RuntimeException(e);
        }
        return cls;
    }

    /**
     * 获取指定报名下的所有以Mapper结尾的类
     *
     * @param packageName
     * @return
     */
    public static Set<Class<?>> getMapperClassSet(String packageName) {
        Set<Class<?>> classSet = new HashSet<>();
        try {
            Enumeration<URL> urls = getClassLoader().getResources(packageName.replace(".", "/"));
            while (urls.hasMoreElements()) {
                URL url = urls.nextElement();
                if (url != null) {
                    // 获取文件类型
                    String protocol = url.getProtocol();
                    if ("file".equals(protocol)) {
                        // 获取本地中的class
                        String packagePath = java.net.URLDecoder.decode(url.getPath().replaceAll("%20", ""), "utf-8");
                        addClass(classSet, packagePath, packageName);
                    } else if ("jar".equals(protocol)) {
                        // 获取jar中的class
                        JarURLConnection jarURLConnection = (JarURLConnection) url.openConnection();
                        if (jarURLConnection != null) {
                            JarFile jarFile = jarURLConnection.getJarFile();
                            if (jarFile != null) {
                                Enumeration<JarEntry> jarEntrys = jarFile.entries();
                                while (jarEntrys.hasMoreElements()) {
                                    JarEntry jarEntry = jarEntrys.nextElement();
                                    String jarEntryName = jarEntry.getName();
                                    if (jarEntryName.endsWith(".class")) {
                                        String className = jarEntryName.substring(0, jarEntryName.lastIndexOf(".")).replace(".", "/");
                                        doAddClass(classSet, className);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            LOGGER.error(e.getLocalizedMessage(), e);
        } catch (Exception e) {
            LOGGER.error(e.getLocalizedMessage(), e);
        }
        return classSet;
    }

    /**
     * 添加class到set中
     *
     * @param classSet
     * @param packagePath
     * @param packageName
     * @throws Exception
     */
    private static void addClass(Set<Class<?>> classSet, String packagePath, String packageName) throws Exception {
        File[] files = new File(packagePath).listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                return (file.isFile() && file.getName().endsWith(".class")) || file.isDirectory();
            }
        });
        for (File file : files) {
            String fileName = file.getName();
            // 判断是否是文件，如果是文件则直接载入
            // 如果是文件夹，则拼接文件路径 递归调用addClass
            if (file.isFile()) {
                String className = fileName.substring(0, fileName.lastIndexOf("."));
                // 判断类前边是否存在包名
                if (StringUtils.isNotEmpty(packageName)) {
                    if (className.indexOf("Mapper") < 0) {
                        continue;
                    }
                    className = packageName + "." + className;
                }
                doAddClass(classSet, className);
            } else {
                String subPackagePath = fileName;
                String subPackageName = fileName;
                if (StringUtils.isNotEmpty(packagePath)) {
                    subPackagePath = packagePath + "/" + subPackagePath;
                }
                if (StringUtils.isNotEmpty(packageName)) {
                    subPackageName = packageName + "." + subPackageName;
                }
                addClass(classSet, subPackagePath, subPackageName);
            }
        }
    }

    /**
     * 载入类并且添加到classSet中
     *
     * @param classSet
     * @param className
     * @throws Exception
     */
    private static void doAddClass(Set<Class<?>> classSet, String className) throws Exception {
        Class<?> cls = ReflectionUtil.loadClass(className, false);
        classSet.add(cls);
    }

    /**
     * 通过class获取属性值
     *
     * @param obj
     * @param fieldName 属性名称
     * @return
     * @throws Exception
     */
    public static Object getFieldValueByClass(Object obj, Object fieldName) throws Exception {
        String entityName = obj.getClass().getName();
        Class c = Class.forName(entityName);
        Field idField = c.getDeclaredField(fieldName.toString());
        idField.setAccessible(true);
        Object value = idField.get(obj);
        return value;
    }
}
