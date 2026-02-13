package com.schedule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class ScheduleV2Application {

    public static void main(String[] args) {
        SpringApplication.run(ScheduleV2Application.class, args);
    }
}