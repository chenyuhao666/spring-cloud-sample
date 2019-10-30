package com.xxx.xxx.application;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: chenyuhao
 * @Date: 2019/10/29 18:23
 * @Version 1.0
 */
@SpringBootApplication
@MapperScan("com.xxx.xxx.application.infrastructure.repository.mysql")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
