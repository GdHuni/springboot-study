package com.example.demo.mapper;

import com.example.demo.enity.UserVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 用户信息Mapper层
 */
@Mapper
public interface UserMapper {
    /**
     * 获取用户信息
     * @return
     */
    List<UserVo> getTestInfo();
}
