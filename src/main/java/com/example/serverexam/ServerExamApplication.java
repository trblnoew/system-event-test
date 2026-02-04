package com.example.serverexam;

import com.example.serverexam.config.MonitoringProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableConfigurationProperties(MonitoringProperties.class)
public class ServerExamApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerExamApplication.class, args);
    }

}
