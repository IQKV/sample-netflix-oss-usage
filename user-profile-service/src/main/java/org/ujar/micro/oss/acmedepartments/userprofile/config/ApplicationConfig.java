package org.ujar.micro.oss.acmedepartments.userprofile.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;
import org.ujar.boot.starter.logbook.LogbookJsonBodyFilter;
import org.ujar.boot.starter.logbook.LogbookResponseOnStatus;
import org.ujar.micro.oss.acmedepartments.userprofile.client.DepartmentServiceClient;
import org.ujar.micro.oss.acmedepartments.userprofile.client.impl.DepartmentServiceClientImpl;
import org.ujar.micro.oss.acmedepartments.userprofile.config.properties.RestfulApiResourcesProperties;
import org.ujar.micro.oss.acmedepartments.userprofile.config.properties.RestfulClientProperties;

@Configuration
@LogbookResponseOnStatus
@LogbookJsonBodyFilter
@EnableTransactionManagement
@EnableEurekaClient
@EnableConfigurationProperties({RestfulClientProperties.class, RestfulApiResourcesProperties.class})
public class ApplicationConfig {

  @Bean
  @LoadBalanced
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }

  @Bean
  DepartmentServiceClient departmentServiceClient(
      @Autowired @Qualifier("departmentServiceRestTemplate") RestTemplate restTemplate,
      RestfulApiResourcesProperties apiResourcesProperties
  ) {
    return new DepartmentServiceClientImpl(restTemplate, apiResourcesProperties);
  }
  
}
