package com.example.demo.enity;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户信息实体类
 */
@Data
public class UserVo implements Serializable {
    private Integer id;
    private Integer age;
    private String name;
}
