package com.lcwd.user.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class UserSrviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserSrviceApplication.class, args);
	}

}
