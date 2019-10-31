package com.xxx.xxx.application.ui.controller;

import com.xxx.xxx.application.domain.model.User;
import com.xxx.xxx.application.domain.service.DataSource1Service;
import com.xxx.xxx.application.infrastructure.dao.mysql.DataSource1TestDao;
import com.xxx.xxx.application.infrastructure.dao.mysql.DataSource2TestDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: chenyuhao
 * @Date: 2019/10/30 17:27
 * @Version 1.0
 */
@RequestMapping("/hello")
@RestController
public class HelloWorldController {

    @Autowired
    private DataSource1Service dataSource1Service;

    @GetMapping("/get")
    public String helloWorld(){
        return "hello world";
    }

    @GetMapping("/getData1")
    public User getDataSource1(){
        return dataSource1Service.getUser();
    }

    @GetMapping("/getData2")
    public User getDataSource2(){
        return dataSource1Service.getUser2();
    }

}
