package com.realshovanshah.restroapi;

import org.jsondoc.spring.boot.starter.EnableJSONDoc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.session.FlushMode;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@SpringBootApplication
@EnableJpaRepositories
@EnableJSONDoc
@EnableRedisHttpSession(flushMode = FlushMode.IMMEDIATE)
public class RestroApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestroApiApplication.class, args);
    }

}
