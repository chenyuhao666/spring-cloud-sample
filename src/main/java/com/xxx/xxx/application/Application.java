package com.xxx.xxx.application;

import com.xxx.xxx.application.datasource.DynamicDataSourceConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Import;

/**
 * @Author: chenyuhao
 * @Date: 2019/10/29 18:23
 * @Version 1.0
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@Import({DynamicDataSourceConfig.class})
@MapperScan("com.xxx.xxx.application.infrastructure.repository.mysql")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
