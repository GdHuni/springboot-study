package com.example.demo.controller;

import com.example.demo.ApplicationTests;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserControllerTest  extends ApplicationTests {
    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;


    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    /**
     perform：执行一个 RequestBuilder 请求，返回一个 ResultActions 实例对象，可对请求结果进行期望与其它操作
     get：声明发送一个 get 请求的方法，更多的请求类型可查阅→MockMvcRequestBuilders 文档
     andExpect：添加 ResultMatcher 验证规则，验证请求结果是否正确，验证规则可查阅→MockMvcResultMatchers 文档
     andDo：添加 ResultHandler 结果处理器，比如调试时打印结果到控制台，更多处理器可查阅→MockMvcResultHandlers 文档
     andReturn：返回执行请求的结果，该结果是一个恩 MvcResult 实例对象→MvcResult 文档
     * @throws Exception
     */
    @Test
    public void getUser() throws Exception {
        mockMvc.perform(get("/test")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("user")));
    }


}
