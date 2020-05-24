package com.example.demo.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.lang.Nullable;

/**
 * 该类继承自 AbstractRoutingDataSource 类，
 * 在访问数据库时会调用该类的 determineCurrentLookupKey() 方法获取数据库实例的 key
 * 返回为null时就是用默认的数据源
 */
public class DynamicRoutingDataSource extends AbstractRoutingDataSource {

    @Nullable
    @Override
    protected Object determineCurrentLookupKey() {
        String key = DataSourceHolder.getDataSourceKey();
        System.out.println("使用的数据源是：" +key);
        return key;
    }
}
