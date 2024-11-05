package com.iqkv.incubator.sample.mixnetflixoss.department.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories({"com.iqkv.incubator.sample.mixnetflixoss.department.repository"})
@EnableJpaAuditing
@EnableTransactionManagement
@EnableSpringDataWebSupport
@EnableEurekaClient
@OpenAPIDefinition(info = @Info(title = "Department Service API", version = "24.0.0"))
class ApplicationConfig {
}
