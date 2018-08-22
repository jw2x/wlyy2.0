package com.yihu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SvrRehabilitationApplication {

	public static void main(String[] args) {
		SpringApplication.run(SvrRehabilitationApplication.class, args);
	}
}
