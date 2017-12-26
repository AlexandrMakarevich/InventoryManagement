package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@Configuration
@ComponentScan("com")
@ImportResource("classpath:boot-config.xml")
public class AppRun {

    public static void main( String[] args ) {
        SpringApplication.run(AppRun.class, args);
    }
}
