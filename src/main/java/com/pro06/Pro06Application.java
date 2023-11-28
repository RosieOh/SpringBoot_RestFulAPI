package com.pro06;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

// @SpringBootApplication
// 아래는 스프링 시큐리티 꺼주는거
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class Pro06Application {

    public static void main(String[] args) {
        SpringApplication.run(Pro06Application.class, args);
    }

}
