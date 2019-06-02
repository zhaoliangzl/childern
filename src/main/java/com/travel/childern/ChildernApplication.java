package com.travel.childern;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@ImportResource(locations={"classpath:mykaptcha.xml"})
@SpringBootApplication
public class ChildernApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChildernApplication.class, args);

    }

}
