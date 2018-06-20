package cts.phase3;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
@MapperScan("cts.phase3.persistence.dao")
@ComponentScan(basePackages = "cts.phase3.*")
@SpringBootApplication

public class Phase3Application extends SpringBootServletInitializer {

    /*@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(Phase3Application.class);
    }*/
    public static void main(String[] args) {
        SpringApplication.run(Phase3Application.class, args);
    }
}
