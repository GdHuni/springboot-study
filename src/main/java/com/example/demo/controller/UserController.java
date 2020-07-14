package com.example.demo.controller;

import com.example.demo.enity.UserVo;
import com.example.demo.service.IUserSerice;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import static org.apache.logging.log4j.LogManager.getLogger;


@Controller
public class UserController {

    @Autowired
    IUserSerice serice;

    private Logger logger = getLogger();

    @RequestMapping("/test")
    @ResponseBody
    public ModelMap getTest(){
        ModelMap model = new ModelMap();
        List<UserVo> userVos = serice.getTestInfo();
        model.addAttribute("返回值",userVos);
        logger.info(userVos);
        return model;
    }
}
