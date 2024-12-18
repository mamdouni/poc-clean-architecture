package com.decathlon.domain_name;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableJpaRepositories(basePackages = { "com.decathlon.domain_name.biz_ctx.infra.secondary.database"})
@EntityScan(basePackages = { "com.decathlon.domain_name.biz_ctx.infra.secondary.database"})
@EnableTransactionManagement
@SpringBootApplication(scanBasePackages = {"com.decathlon.domain_name"})
public class PocHexagonalArchitectureApplication {

	public static void main(String[] args) {
		SpringApplication.run(PocHexagonalArchitectureApplication.class, args);
	}

}
