package com.example.demo.controller;

import com.example.demo.enity.UserVo;
import com.example.demo.service.IUserSerice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    IUserSerice serice;

    @RequestMapping("/test")
    @ResponseBody
    public ModelMap getTest(){
        ModelMap model = new ModelMap();
        List<UserVo> userVos = serice.getTestInfo();
        model.addAttribute("返回值",userVos);
        return model;
    }
}
