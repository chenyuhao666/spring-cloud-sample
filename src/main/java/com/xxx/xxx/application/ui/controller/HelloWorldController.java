package com.xxx.xxx.application.ui.controller;

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

    @GetMapping("/get")
    public String helloWorld(){
        return "hello world";
    }
}
