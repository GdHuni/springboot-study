package com.example.demo.service.impl;

import com.example.demo.ApplicationTests;
import com.example.demo.enity.UserVo;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.IUserSerice;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

//启动Spring
public class UserServiceImplTest extends ApplicationTests{

/*
    @MockBean
    private IUserSerice userSericeMock;
*/

    @Autowired
    private IUserSerice userSerice;

    @Autowired
    private UserMapper userMapper;
/*
    @Test
    public void getTestInfoOnMock() {
        //Mockito 就是只测业务逻辑而不会访问数据库，里面直接指定mapper返回的数据
        UserVo userVo = new UserVo();
        userVo.setAge(22);
        userVo.setId(1);
        userVo.setName("zh");
        List<UserVo> list = new ArrayList<>();
        list.add(userVo);
        Mockito.when(userMapper.getTestInfo()).thenReturn(list);
        List<UserVo> testInfo = userSericeMock.getTestInfo();
        assertTrue(testInfo.size()>=1);
    }*/


    @Test
    public void getTestInfo() {
        List<UserVo> testInfo = userSerice.getTestInfo();
        assertTrue(testInfo.size()>=1);
    }

}