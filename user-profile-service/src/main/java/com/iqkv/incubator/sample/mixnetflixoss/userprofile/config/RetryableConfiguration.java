/*
 * Copyright 2024 IQKV Team.
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

package com.iqkv.incubator.sample.mixnetflixoss.userprofile.config;

import com.iqkv.incubator.sample.mixnetflixoss.userprofile.config.properties.RestfulClientProperties;
import com.iqkv.incubator.sample.mixnetflixoss.userprofile.config.properties.RetryProperties;
import com.iqkv.incubator.sample.mixnetflixoss.userprofile.exception.ClientErrorDownstreamException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.backoff.ExponentialBackOffPolicy;
import org.springframework.retry.interceptor.RetryOperationsInterceptor;
import org.springframework.retry.support.RetryTemplate;

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
