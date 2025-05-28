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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iqkv.sample.mixnetflixoss.userprofile.client.error.CommonErrorHandler;
import com.iqkv.sample.mixnetflixoss.userprofile.config.properties.RestfulApiResourcesProperties;
import com.iqkv.sample.mixnetflixoss.userprofile.config.properties.RestfulClientProperties;
import com.iqkv.sample.mixnetflixoss.userprofile.exception.Downstream;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfiguration {

  @Bean(name = "departmentServiceRestTemplate")
  @LoadBalanced
  RestTemplate departmentServiceRestTemplate(RestfulClientProperties httpClientProperties, RestfulApiResourcesProperties api,
                                             ObjectMapper objectMapper) {
    return restTemplateBuilder(httpClientProperties)
        .rootUri(api.getDepartmentServiceBaseUrl())
        .errorHandler(new CommonErrorHandler(
                objectMapper,
                Downstream.DEPARTMENT_SERVICE
            )
        )
        .build();
  }

  RestTemplateBuilder restTemplateBuilder(RestfulClientProperties httpClientProperties) {
    final var httpClient = getHttpClient(httpClientProperties.getMaxConnPerRoute(),
        httpClientProperties.getMaxConnTotal());
    return new RestTemplateBuilder()
        .requestFactory(() -> new HttpComponentsClientHttpRequestFactory(httpClient))
        .setConnectTimeout(httpClientProperties.getConnectTimeout())
        .setReadTimeout(httpClientProperties.getReadTimeout());
  }

  HttpClient getHttpClient(int maxConnPerRoute, int maxConnTotal) {
    return HttpClientBuilder.create()
        .setMaxConnPerRoute(maxConnPerRoute)
        .setMaxConnTotal(maxConnTotal)
        .build();
  }
}
