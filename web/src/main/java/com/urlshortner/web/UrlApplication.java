package com.urlshortner.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@ComponentScan("com.urlshortner.web.*")
@ComponentScan("com.urlshortner.core.*")
@ComponentScan("com.urlshortner.repo.*")
@EntityScan(basePackages = "com.urlshortner.repo.domain")
@EnableJpaRepositories(basePackages = "com.urlshortner.repo.database")
@EnableTransactionManagement
public class UrlApplication {
	public static void main(String[] args) {
		SpringApplication.run(UrlApplication.class, args);
	}
}
