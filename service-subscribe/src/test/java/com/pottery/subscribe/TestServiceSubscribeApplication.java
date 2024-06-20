package com.pottery.subscribe;

import org.springframework.boot.SpringApplication;

public class TestServiceSubscribeApplication {

    public static void main(String[] args) {
        SpringApplication.from(ServiceSubscribeApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
