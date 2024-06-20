package com.urlshortner.repo.database;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("com.urlshortner.web.*")
@ComponentScan("com.urlshortner.core.*")
@ComponentScan("com.urlshortner.repo.*")
@EntityScan(basePackages = "com.urlshortner.repo.domain")
@EnableJpaRepositories(basePackages = "com.urlshortner.repo.database")
public class TestApplication {
}
