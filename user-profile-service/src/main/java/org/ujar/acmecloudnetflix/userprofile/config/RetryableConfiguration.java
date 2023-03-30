package org.ujar.acmecloudnetflix.userprofile.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.backoff.ExponentialBackOffPolicy;
import org.springframework.retry.interceptor.RetryOperationsInterceptor;
import org.springframework.retry.support.RetryTemplate;
import org.ujar.acmecloudnetflix.userprofile.config.properties.RestfulClientProperties;
import org.ujar.acmecloudnetflix.userprofile.config.properties.RetryProperties;
import org.ujar.acmecloudnetflix.userprofile.exception.ClientErrorDownstreamException;

@EnableRetry
@Configuration
public class RetryableConfiguration {

  @Bean
  public RetryOperationsInterceptor departmentServiceRetryInterceptor(RestfulClientProperties properties) {
    return getRetryOperationsInterceptor(properties.getRetry());
  }

  private RetryOperationsInterceptor getRetryOperationsInterceptor(RetryProperties retry) {
    final var backOffPolicy = new ExponentialBackOffPolicy();
    backOffPolicy.setInitialInterval(retry.getDelay());
    backOffPolicy.setMultiplier(retry.getMultiplier());
    backOffPolicy.setMaxInterval(retry.getMaxDelay());
    final var retryOperationsInterceptor = new RetryOperationsInterceptor();
    retryOperationsInterceptor.setRetryOperations(RetryTemplate.builder()
        .notRetryOn(ClientErrorDownstreamException.class)
        .maxAttempts(retry.getMaxAttempts())
        .customBackoff(backOffPolicy)
        .build());
    return retryOperationsInterceptor;
  }
}
