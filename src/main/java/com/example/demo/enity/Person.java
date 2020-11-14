package com.example.demo.enity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "person")
@ApiModel("人员信息类")
public class Person {
    @ApiModelProperty("姓名")
    public String name;
    @ApiModelProperty("年龄")
    public int age;

}

