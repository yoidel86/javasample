package com.musalatest.dronetest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class DronetestApplication {
	public static void main(String[] args) {
		SpringApplication.run(DronetestApplication.class, args);
	}
}
