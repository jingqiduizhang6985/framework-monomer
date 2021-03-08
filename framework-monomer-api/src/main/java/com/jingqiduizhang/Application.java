package com.jingqiduizhang;

import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//springBoot 启动类注解
@SpringBootApplication
// 扫描 mybatis 通用 mapper 所在的包
@MapperScan(basePackages = "com.jingqiduizhang.mapper")
// 扫描所有包以及相关组件包  引入 org.n3r.idworker 用来生成唯一id的工具包
@ComponentScan(basePackages = {"com.jingqiduizhang", "org.n3r.idworker"})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
}
