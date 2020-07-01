package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @功能描述: 单元测试超类
 * 单元测试不能使用org.junit.jupiter.api.Test
 * 要使用 org.junit.Test;
 * 类和方法都必须使用public
 */
@RunWith(SpringRunner.class)
//@SpringBootTest(classes={Application.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@SpringBootTest
public class ApplicationTests {

	@Test
	public void contextLoads() {
		System.out.println(1);
	}


}
