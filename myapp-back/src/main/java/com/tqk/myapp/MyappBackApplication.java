package com.tqk.myapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
//@EnableJms
@EnableScheduling
public class MyappBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyappBackApplication.class, args);
	}

}
