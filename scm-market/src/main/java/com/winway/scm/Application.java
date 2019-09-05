package com.winway.scm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
@MapperScan(basePackages={"com.hotent.**.dao","com.winway.**.dao"})
@ComponentScan({"com.hotent.*","com.winway.*"})
//@EnableFeignClients(basePackages = {"com.hotent.*", "com.winway.*"})
public class Application 
{
	public static void main( String[] args )
	{
		SpringApplication.run(Application.class, args);
	}
}