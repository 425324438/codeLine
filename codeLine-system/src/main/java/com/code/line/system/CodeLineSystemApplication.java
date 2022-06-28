package com.code.line.system;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.statemachine.config.EnableStateMachine;

@EnableStateMachine
@SpringBootApplication
@MapperScan("com.code.line.system.mapper")
public class CodeLineSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(CodeLineSystemApplication.class, args);
    }

}
