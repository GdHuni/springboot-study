package com.example.demo.config;

import com.example.demo.datasource.DataSourceHolder;
import com.example.demo.datasource.DynamicRoutingDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DataSourceConfig {

    /**
     * 获取master数据源
     * @return
     */
    @Bean("master")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.master")
    public DataSource master(){
        return DataSourceBuilder.create().build();
    }

    /**
     * 获取slave数据源
     * @return
     */
    @Bean("slave")
    @ConfigurationProperties(prefix = "spring.datasource.slave")
    public DataSource slave(){
        return DataSourceBuilder.create().build();
    }

    /**
     * 获取dynamicDataSource数据源
     * @return
     */
    @Bean("dynamicDataSource")
    public DataSource dynamicDataSource(){
        //当使用该数据源时就会DynamicRoutingDataSource
        DynamicRoutingDataSource dynamicRoutingDataSource = new DynamicRoutingDataSource();
        Map map = new HashMap();
        map.put("master",master());
        map.put("slave",slave());

        // 将 master 数据源作为默认指定的数据源
        dynamicRoutingDataSource.setDefaultTargetDataSource(master());
        // 将 master 和 slave 数据源作为指定的数据源
        dynamicRoutingDataSource.setTargetDataSources(map);

        // 将数据源的 key 放到数据源上下文的 key 集合中，用于切换数据源
        DataSourceHolder.dataSourceKeys.addAll(map.keySet());

        return  dynamicRoutingDataSource;
    }

    @Bean(name = "mySqlSessionFactory")
    public SqlSessionFactory mySqlSessionFactory(/*@Qualifier("master") DataSource dataSource*/) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
       // factoryBean.setDataSource(dataSource);
        factoryBean.setDataSource(dynamicDataSource());
        //指定mapper路径
        factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml"));
        //打印sql语句
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setLogImpl(org.apache.ibatis.logging.stdout.StdOutImpl.class);
        factoryBean.setConfiguration(configuration);

        return factoryBean.getObject();
    }

    @Bean(name="mySqlTransactionManager")
    public DataSourceTransactionManager mySqlTransactionManager(/*@Qualifier("master") DataSource dataSource*/){
            return new DataSourceTransactionManager(dynamicDataSource());
        //return new DataSourceTransactionManager(dataSource);
    }
/*
    @Bean(name = "mySqlSessionTemplate")
    public SqlSessionTemplate mySqlSessionTemplate(@Qualifier("mySqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean(name = "mySqlJdbcTemplate")
    *//*@Qualifier("master")*//*
    public JdbcTemplate mySqlJdbcTemplate(*//*@Qualifier("master")*//* DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }*/

}
