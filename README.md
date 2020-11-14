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

#### flink
~~~~
新增flinkdemo代码
~~~~

#### 单元测试
~~~~
单元测试超类：com.example.demo.ApplicationTests
https://juejin.im/post/5d62cc3ee51d45620b21c3e9#heading-2
~~~~

#### log4j日志框架
~~~~
配置log4j2日志输出
1.		pom.xml加入log4j2，并同时把spring boot默认的logging去掉
<!-- 配置 log4j2 -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-log4j2</artifactId>
</dependency>
<!-- 加上这个才能辨认到log4j2.yml文件 -->
<dependency>
    <groupId>com.fasterxml.jackson.dataformat</groupId>
    <artifactId>jackson-dataformat-yaml</artifactId>
</dependency>
  		
2.去掉默认日志输出
<dependency>
    <groupId>com.github.pagehelper</groupId>
    <artifactId>pagehelper-spring-boot-starter</artifactId>
    <version>1.2.5</version>
    <exclusions>
        <exclusion>
            <groupId>org.mybatis.spring.boot</groupId>
            <artifactId>mybatis-spring-boot-starter</artifactId>
        </exclusion>
        <!-- 切换log4j2日志读取 -->
        <exclusion>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-logging</artifactId>
        </exclusion>
    </exclusions>
</dependency>
<dependency>
    <groupId>org.mybatis.spring.boot</groupId>
    <artifactId>mybatis-spring-boot-starter</artifactId>
    <exclusions>
        <!-- 切换log4j2日志读取 -->
        <exclusion>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-logging</artifactId>
        </exclusion>
    </exclusions>
    <version>2.0.0</version>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-jdbc</artifactId>
    <exclusions>
        <!-- 切换log4j2日志读取 -->
        <exclusion>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-logging</artifactId>
        </exclusion>
    </exclusions>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
    <exclusions>
        <exclusion> <!-- 去掉默认配置 -->
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-logging</artifactId>
        </exclusion>
    </exclusions>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter</artifactId>
    <exclusions>
        <exclusion> <!-- 去掉默认配置 -->
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-logging</artifactId>
        </exclusion>
    </exclusions>
</dependency>
3.配置log4j2.yml文件
4.org.apache.logging.log4j.LogManager.getLogger;  
    private Logger logger = getLogger();
~~~~

#### swagger2及UI增强使用
~~~~
1.配置SwaggerConfig（详见文件）
2.如果使用的原始swagger2需要在启动类加上@EnableSwagger2 否则报错
3.给类，接口，参数，实体类添加解释说明
4.通过http://ip+port/项目名/doc.html# 访问 http://localhost:8081/huni/doc.html#
~~~~