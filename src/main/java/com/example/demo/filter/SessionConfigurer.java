package com.example.demo.filter;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @功能描述: 拦截配置器
 * @项目版本: 1.0.0
 * @项目名称: 自助分析平台
 * @相对路径: com.leyoujia.bigdata.filter.SessionConfigurer
 * @创建作者: <a href="mailto:ouyangwenbin2009@live.cn">欧阳文斌</a>
 * @创建日期: 2019-03-21 10:24:20
 */
@Configuration
public class SessionConfigurer implements WebMvcConfigurer {

    /**
     * 这个方法是用来配置静态资源的，比如html，js，css，等等
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
        WebMvcConfigurer.super.addResourceHandlers(registry);

    }

}
