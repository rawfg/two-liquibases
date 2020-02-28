package com.problem.two.liquibases;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseAutoConfiguration;

@SpringBootApplication(exclude = LiquibaseAutoConfiguration.class)
public class TwoLiquibasesApplication {

	public static void main(String[] args) {
		SpringApplication.run(TwoLiquibasesApplication.class, args);
	}

}
