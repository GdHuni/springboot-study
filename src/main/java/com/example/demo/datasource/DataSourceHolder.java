package com.example.demo.datasource;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 数据源处理类，用于动态切换数据源的一些逻辑处理
 */
public class DataSourceHolder {

    /** 计数器 */
    private static int counter;

    /**
     * 用于在切换数据源时保证不会被其他线程修改
     */
    private static Lock lock = new ReentrantLock();

    /**
     * 线程级别的私有变量
     */
    private static final ThreadLocal<String> CONTEXT_HOLDER = new ThreadLocal<>();

    /**
     * 数据源key集合
     */
    public static List<Object> dataSourceKeys = new ArrayList<>();

    /**
     * 获取数据源key
     */
    public static String getDataSourceKey() {
        return CONTEXT_HOLDER.get();
    }

    /**
     * 设置数据源key
     */
    public static void setDataSourceKey(String key) {
        CONTEXT_HOLDER.set(key);
    }

    /**
     * Use master data source.
     */
    public static void useMasterDataSource() {
        CONTEXT_HOLDER.set("master");
    }

    /**
     * 当使用只读数据源时通过轮循方式选择要使用的数据源
     */
    public static void useDataSource() {
        lock.lock();

        try {
            int datasourceKeyIndex = counter % dataSourceKeys.size();
            CONTEXT_HOLDER.set(String.valueOf(dataSourceKeys.get(datasourceKeyIndex)));
            counter++;
        } catch (Exception e) {
            //logger.error("Switch slave datasource failed, error message is {}", e.getMessage());
            useMasterDataSource();
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


    /**
     * To set DataSource as default
     */
    public static void clearDataSourceKey() {
        CONTEXT_HOLDER.remove();
    }

    public static boolean containDataSourceKey(String key) {
        return dataSourceKeys.contains(key);
    }
}
