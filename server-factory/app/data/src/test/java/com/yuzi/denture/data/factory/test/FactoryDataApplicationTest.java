package com.yuzi.denture.data.factory.test;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan(basePackages={"com.yuzi.denture.data.factory"})
@MapperScan(basePackages = {"com.yuzi.denture.data.factory.mapper"})
public class FactoryDataApplicationTest {

    public static void main(String[] args) {
        SpringApplication.run(FactoryDataApplicationTest.class, args);
    }

}
