package com.daniel22c.DIYWEB;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;


//public class Application {
//    public static void main(String[] args) {
//		SpringApplication.run(Application.class, args);
//	}
//}

//@EnableAutoConfiguration
//@ComponentScan(basePackages = "com.daniel22c.DIYWEB")
@SpringBootApplication
public class Application extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }

}
