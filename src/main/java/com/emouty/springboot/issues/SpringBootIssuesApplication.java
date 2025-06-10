package com.emouty.springboot.issues;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "com.emouty.springboot")
@SpringBootApplication
public class SpringBootIssuesApplication {

    public static void main(String[] args) {
        SpringApplicationBuilder myApp = new SpringApplicationBuilder(SpringBootIssuesApplication.class);
        myApp.environmentPrefix("myapp");
        myApp.run(args);
    }

}
