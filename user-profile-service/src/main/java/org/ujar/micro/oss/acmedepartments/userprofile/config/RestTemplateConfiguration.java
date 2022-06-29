package org.ujar.micro.oss.acmedepartments.userprofile.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.ujar.micro.oss.acmedepartments.userprofile.client.error.CommonErrorHandler;
import org.ujar.micro.oss.acmedepartments.userprofile.config.properties.RestfulApiResourcesProperties;
import org.ujar.micro.oss.acmedepartments.userprofile.config.properties.RestfulClientProperties;
import org.ujar.micro.oss.acmedepartments.userprofile.exception.Downstream;
import org.zalando.logbook.Logbook;
import org.zalando.logbook.httpclient.LogbookHttpRequestInterceptor;
import org.zalando.logbook.httpclient.LogbookHttpResponseInterceptor;


@Configuration
public class RestTemplateConfiguration {

  @Bean(name = "departmentServiceRestTemplate")
  @LoadBalanced
  RestTemplate departmentServiceRestTemplate(
      RestfulClientProperties httpClientProperties, Logbook logbook,
      RestfulApiResourcesProperties api,
      ObjectMapper objectMapper) {
    return restTemplateBuilder(httpClientProperties, logbook)
        .rootUri(api.getDepartmentServiceBaseUrl())
        .errorHandler(new CommonErrorHandler(
                objectMapper,
                Downstream.DEPARTMENT_SERVICE
            )
        )
        .build();
  }

  RestTemplateBuilder restTemplateBuilder(RestfulClientProperties httpClientProperties, Logbook logbook) {
    var httpClient = getHttpClient(logbook, httpClientProperties.getMaxConnPerRoute(),
        httpClientProperties.getMaxConnTotal());
    return new RestTemplateBuilder()
        .requestFactory(() -> new HttpComponentsClientHttpRequestFactory(httpClient))
        .setConnectTimeout(httpClientProperties.getConnectTimeout())
        .setReadTimeout(httpClientProperties.getReadTimeout());
  }

  HttpClient getHttpClient(Logbook logbook,
                           int maxConnPerRoute,
                           int maxConnTotal) {
    return HttpClientBuilder.create()
        .setMaxConnPerRoute(maxConnPerRoute)
        .setMaxConnTotal(maxConnTotal)
        .addInterceptorFirst(new LogbookHttpRequestInterceptor(logbook))
        .addInterceptorLast(new LogbookHttpResponseInterceptor())
        .build();
  }
}
