package com.example.demo.datasource;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 动态数据源切面类，切dao层
 */
@Aspect
@Component
public class DataSourceAspect {

    /**
     * 定义切入点，当执行到切入点的时候就会执行该切面里的方法
     */
    @Pointcut("execution( * com.example.demo.mapper.*.*(..))")
    public void daoAspect(){

    }

    /**
     * 调用随机轮询数据源方法
     */
    @Before("daoAspect()")
    public void initDataSource(){
        DataSourceHolder.useDataSource();
    }

    /**
     * 清空本地线程中的数据源key
     * @param point
     */
    @After("daoAspect()")
    public void restoreDataSource(JoinPoint point) {
        DataSourceHolder.clearDataSourceKey();
       // logger.info("Restore DataSource to [{}] in Method [{}]",DataSourceHolder.getDataSourceKey(), point.getSignature());
    }
}
