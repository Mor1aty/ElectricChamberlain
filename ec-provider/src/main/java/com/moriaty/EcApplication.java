package com.moriaty;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.moriaty.mapper")
public class EcApplication {
    public static void main(String[] args) {
        SpringApplication.run(EcApplication.class, args);
    }
}
