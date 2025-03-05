package com.wangwang.management;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.wangwang.management.mapper")
public class BackgroundManagementApplication {


    public static void main(String[] args) {
        SpringApplication.run(BackgroundManagementApplication.class, args);
    }
}
