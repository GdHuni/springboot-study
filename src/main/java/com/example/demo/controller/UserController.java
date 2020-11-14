package com.example.demo.controller;

import com.example.demo.enity.Person;
import com.example.demo.enity.UserVo;
import com.example.demo.service.IUserSerice;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import static org.apache.logging.log4j.LogManager.getLogger;

@Api("给类添加说明的注释")
@Controller
public class UserController {

    @Autowired
    IUserSerice serice;

    private Logger logger = getLogger();

    @GetMapping("/test")
    @ResponseBody
    @ApiOperation(value = "就是这个方法的说明啦",notes="就是这个接口的说明啦")
    //给参数添加说明的注释
    @ApiImplicitParams({@ApiImplicitParam(name = "id",value = "ID"),
            @ApiImplicitParam(name = "name",value = "姓名")})
    public ModelMap getTest(String id, String name){
        ModelMap model = new ModelMap();
        List<UserVo> userVos = serice.getTestInfo();
        model.addAttribute("返回值",userVos);
        logger.info(userVos);
        return model;
    }

    @GetMapping("/test1")
    @ResponseBody
    @ApiOperation(value = "使用实体类参数添加说明",notes="就是这个接口的说明啦")
    public ModelMap getTest(Person person){
        ModelMap model = new ModelMap();
        List<UserVo> userVos = serice.getTestInfo();
        model.addAttribute("返回值",userVos);
        logger.info(userVos);
        return model;
    }
}
