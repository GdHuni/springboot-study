package com.example.demo.datasource;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @功能描述: 采用注解来设置数据源，通过aop拦截并且保存到数据源上下中。新建一个标识数据源的注解@DataSource
 *
 * @使用对象: 自助分析
 * @接口版本: 1.5.0
 * @创建作者: <a href="mailto:zhouh@leyoujia.com">周虎</a>
 * @创建日期: 2020/05/3 15:43
 */
@Target({ElementType.METHOD, ElementType.TYPE, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataSource {
    String value() default ""; //该值即key值
}
