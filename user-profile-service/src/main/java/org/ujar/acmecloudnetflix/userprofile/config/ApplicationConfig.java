package org.ujar.acmecloudnetflix.userprofile.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;
import org.ujar.acmecloudnetflix.userprofile.client.DepartmentServiceClient;
import org.ujar.acmecloudnetflix.userprofile.client.DepartmentServiceClientImpl;
import org.ujar.acmecloudnetflix.userprofile.config.properties.RestfulApiResourcesProperties;
import org.ujar.acmecloudnetflix.userprofile.config.properties.RestfulClientProperties;

@Configuration
@EnableTransactionManagement
@EnableEurekaClient
@EnableConfigurationProperties({RestfulClientProperties.class, RestfulApiResourcesProperties.class})
class ApplicationConfig {

  @Bean
  DepartmentServiceClient departmentServiceClient(
      @Autowired @Qualifier("departmentServiceRestTemplate") RestTemplate restTemplate,
      RestfulApiResourcesProperties apiResourcesProperties
  ) {
    return new DepartmentServiceClientImpl(restTemplate, apiResourcesProperties);
  }
  
}
