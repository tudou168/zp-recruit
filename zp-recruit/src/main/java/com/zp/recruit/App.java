package com.zp.recruit;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.MultipartAutoConfiguration;

@SpringBootApplication
@MapperScan("com.zp.recruit.mapper")
@EnableAutoConfiguration(exclude = {MultipartAutoConfiguration.class})
public class App {
	
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
	 

} 
