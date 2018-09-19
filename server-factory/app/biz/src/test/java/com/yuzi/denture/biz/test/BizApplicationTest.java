package com.yuzi.denture.biz.test;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan(basePackages={"com.yuzi.denture.biz"})
@ComponentScan(basePackages={"com.yuzi.denture.data"})
@MapperScan(basePackages = {"com.yuzi.denture.data.mapper"})
public class BizApplicationTest {

    public static void main(String[] args) {
        SpringApplication.run(BizApplicationTest.class, args);
    }

}
