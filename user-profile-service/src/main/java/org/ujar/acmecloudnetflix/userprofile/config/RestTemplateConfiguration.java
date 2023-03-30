package org.ujar.acmecloudnetflix.userprofile.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.ujar.acmecloudnetflix.userprofile.client.error.CommonErrorHandler;
import org.ujar.acmecloudnetflix.userprofile.config.properties.RestfulApiResourcesProperties;
import org.ujar.acmecloudnetflix.userprofile.config.properties.RestfulClientProperties;
import org.ujar.acmecloudnetflix.userprofile.exception.Downstream;

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
