package com._thefull.dasom_web_demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@EnableJpaAuditing
@SpringBootApplication
public class DasomWebDemoApplication {
	public static void main(String[] args) {
		SpringApplication.run(DasomWebDemoApplication.class, args);
	}

}
