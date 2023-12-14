package dev.knowhowto.acmecloudnetflix.department.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories({"dev.knowhowto.acmecloudnetflix.department.repository"})
@EnableJpaAuditing
@EnableTransactionManagement
@EnableSpringDataWebSupport
@EnableEurekaClient
@OpenAPIDefinition(info = @Info(title = "Department Service API", version = "23.0.0"))
class ApplicationConfig {
}
