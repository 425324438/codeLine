package com.code.line.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.statemachine.config.EnableStateMachine;

@EnableStateMachine
@SpringBootApplication
public class CodeLineSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(CodeLineSystemApplication.class, args);
    }

}
