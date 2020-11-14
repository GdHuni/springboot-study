package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2//使用增強版後就可以不需要此注解
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);

	}

}
