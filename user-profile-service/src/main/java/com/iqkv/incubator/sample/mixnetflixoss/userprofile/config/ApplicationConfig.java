package com.iqkv.incubator.sample.mixnetflixoss.userprofile.config;

import com.iqkv.incubator.sample.mixnetflixoss.userprofile.client.DepartmentServiceClient;
import com.iqkv.incubator.sample.mixnetflixoss.userprofile.client.DepartmentServiceClientImpl;
import com.iqkv.incubator.sample.mixnetflixoss.userprofile.config.properties.RestfulApiResourcesProperties;
import com.iqkv.incubator.sample.mixnetflixoss.userprofile.config.properties.RestfulClientProperties;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableTransactionManagement
@EnableEurekaClient
@EnableConfigurationProperties({RestfulClientProperties.class, RestfulApiResourcesProperties.class})
@OpenAPIDefinition(info = @Info(title = "User Profile Service API", version = "24.0.0"))
class ApplicationConfig {

  @Bean
  DepartmentServiceClient departmentServiceClient(
      @Autowired @Qualifier("departmentServiceRestTemplate") RestTemplate restTemplate,
      RestfulApiResourcesProperties apiResourcesProperties
  ) {
    return new DepartmentServiceClientImpl(restTemplate, apiResourcesProperties);
  }

}
