package com.example.demo.service;

import com.example.demo.enity.UserVo;

import java.util.List;

/**
 * userService层
 */
public interface IUserSerice {
    /**
     * 获取用户信息
     * @return
     */
    List<UserVo> getTestInfo();
}
