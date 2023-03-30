package org.ujar.acmecloudnetflix.department.config;

import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories({"org.ujar.acmecloudnetflix.department.repository"})
@EnableJpaAuditing
@EnableTransactionManagement
@EnableSpringDataWebSupport
@EnableEurekaClient
class ApplicationConfig {
}
