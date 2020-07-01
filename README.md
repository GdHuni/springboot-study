#### springboot+mybatis 配置动态多数据源

~~~~
-- 建表
CREATE TABLE `USER_TEST` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
 
  `NAME` varchar(10) COLLATE utf8_unicode_ci NOT NULL COMMENT '姓名',
  `AGE` INT(11) COLLATE utf8_unicode_ci NOT NULL COMMENT '年龄',
  `UPDATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='用户测试表';
~~~~


~~~~
0.yml文件中spring.datasource.url要改为spring.datasource.jdbc-url
1.写config配置文件,加载yml文件中的数据源。并且将所有的数据源加载到DynamicRoutingDataSource中设置，然后使用该数据源。后续通过匹配到的key走数据源
2.写切面类DynamicDataSourceAspect 拦截mapper的请求。  
3.写数据源处理类DataSourceHolder 用于指定查询那个数据源   
4.写数据源查询类 指定sql查询那个数据源DynamicRoutingDataSource extends AbstractRoutingDataSource 在访问数据库时会调用该类的 determineCurrentLookupKey() 方法获取数据库实例的 key
~~~~

~~~~
新增flinkdemo代码
~~~~

~~~~
单元测试超类：com.example.demo.ApplicationTests
~~~~
