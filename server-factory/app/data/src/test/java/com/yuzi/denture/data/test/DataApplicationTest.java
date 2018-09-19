package com.yuzi.denture.data.test;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan(basePackages={"com.yuzi.denture.data.factory"})
@MapperScan(basePackages = {"com.yuzi.denture.data.factory.mapper"})
public class DataApplicationTest {

    public static void main(String[] args) {
        SpringApplication.run(DataApplicationTest.class, args);
    }

}
