/*
 * Copyright 2025 IQKV Foundation Team.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.iqkv.sample.mixnetflixoss.userprofile.config;

import com.iqkv.sample.mixnetflixoss.userprofile.client.DepartmentServiceClient;
import com.iqkv.sample.mixnetflixoss.userprofile.client.DepartmentServiceClientImpl;
import com.iqkv.sample.mixnetflixoss.userprofile.config.properties.RestfulApiResourcesProperties;
import com.iqkv.sample.mixnetflixoss.userprofile.config.properties.RestfulClientProperties;
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
@OpenAPIDefinition(info = @Info(title = "User Profile Service API", version = "25.0.0"))
class ApplicationConfig {

  @Bean
  DepartmentServiceClient departmentServiceClient(
      @Autowired @Qualifier("departmentServiceRestTemplate") RestTemplate restTemplate,
      RestfulApiResourcesProperties apiResourcesProperties
  ) {
    return new DepartmentServiceClientImpl(restTemplate, apiResourcesProperties);
  }

}
