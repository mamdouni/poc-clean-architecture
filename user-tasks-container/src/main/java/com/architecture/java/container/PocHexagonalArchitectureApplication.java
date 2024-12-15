package com.architecture.java.container;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = { "com.architecture.java.infra.secondary.database"})
@EntityScan(basePackages = { "com.architecture.java.infra.secondary.database"})
@SpringBootApplication(scanBasePackages = {"com.architecture.java"})
public class PocHexagonalArchitectureApplication {

	public static void main(String[] args) {
		SpringApplication.run(PocHexagonalArchitectureApplication.class, args);
	}

}
