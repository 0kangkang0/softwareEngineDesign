package com.kangkang;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@Slf4j
@MapperScan("com.kangkang.mapper")
@ServletComponentScan
@PropertySource("classpath:filePath.properties")
public class SoftwareEngineDesignApplication {

    public static void main(String[] args) {
        SpringApplication.run(SoftwareEngineDesignApplication.class, args);
    }

}
