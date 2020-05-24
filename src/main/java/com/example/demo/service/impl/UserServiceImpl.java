package com.example.demo.service.impl;

import com.example.demo.enity.UserVo;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.IUserSerice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserSerice {
    @Autowired
    UserMapper testMapper;

    @Override
    public List<UserVo> getTestInfo() {
        List<UserVo> testInfo = testMapper.getTestInfo();

        return testInfo;
    }
}
