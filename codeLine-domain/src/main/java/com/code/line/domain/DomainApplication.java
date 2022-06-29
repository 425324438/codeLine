package com.code.line.domain;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.code.line")
@MapperScan("com.code.line.system.mapper")
public class DomainApplication {

    public static void main(String[] args) {
        SpringApplication.run(DomainApplication.class, args);
    }

}
