package com.tudou.util;

import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import java.util.Set;

/**
 * Mbatis工具
 *
 * @author weihua
 * @create 2017-07-06 22:16
 */
public final class MybatisUtil {
    private static SqlSessionFactory sqlSessionFactory = null;

    private static SqlSession sqlSession = null;

    private final static String PACKAGE = "com.tudou";

    /**
     * 获取会话
     *
     * @return
     */
    public static SqlSession getSqlSession() {
        buildSqlSessionFactory();
        if (sqlSession == null) {
            sqlSession = sqlSessionFactory.openSession();
        }
        return sqlSession;
    }

    /**
     * 关闭会话
     */
    public static void closeSqlSession() {
        if (sqlSession != null) {
            sqlSession.close();
            sqlSession = null;
        }
    }

    /**
     * 构建session工厂
     */
    private static void buildSqlSessionFactory() {
        if (sqlSessionFactory == null) {
            PooledDataSource dataSource = new PooledDataSource();
            dataSource.setDriver(PropertiesUtil.getValue("jdbc.driver"));
            dataSource.setUrl(PropertiesUtil.getValue("jdbc.url"));
            dataSource.setUsername(PropertiesUtil.getValue("jdbc.username"));
            dataSource.setPassword(PropertiesUtil.getValue("jdbc.password"));
            TransactionFactory transactionFactory = new JdbcTransactionFactory();
            Environment environment = new Environment("development", transactionFactory, dataSource);
            Configuration configuration = new Configuration(environment);
            // 添加所有以Mapper结尾的映射类
            for (Class c : scanPackage()) {
                configuration.addMapper(c);
            }
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
        }
    }

    /**
     * 扫描所有 com.tudou.**.mapper下的类
     *
     * @return
     */
    private static Set<Class<?>> scanPackage() {
        return ReflectionUtil.getMapperClassSet(PACKAGE);
    }

}
