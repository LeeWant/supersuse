package com.lee.supersuse;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


@SpringBootApplication
@ServletComponentScan
@MapperScan("com.lee.supersuse.mapper")
// extends SpringBootServletInitializer
public class SupersuseApplication {

    public static void main(String[] args) {
        SpringApplication.run(SupersuseApplication.class, args);
    }
//    @Override//为了打包springboot项目
//    protected SpringApplicationBuilder configure(
//            SpringApplicationBuilder builder) {
//        return builder.sources(SupersuseApplication.class);
//    }
}

