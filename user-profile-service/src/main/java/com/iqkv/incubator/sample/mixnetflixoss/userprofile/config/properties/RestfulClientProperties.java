/*
 * Copyright 2025 IQKV Team.
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

package com.iqkv.incubator.sample.mixnetflixoss.userprofile.config.properties;

import java.time.Duration;

import lombok.NonNull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.validation.annotation.Validated;

@ConstructorBinding
@Validated
@ConfigurationProperties(prefix = "iqkv.restful-client")
public record RestfulClientProperties(
    @NonNull Duration connectTimeout,
    @NonNull Duration readTimeout,
    int maxConnPerRoute,
    int maxConnTotal,
    @NonNull RetryProperties retry
) {

  public Duration getConnectTimeout() {
    return connectTimeout();
  }


  public Duration getReadTimeout() {
    return readTimeout();
  }


  public int getMaxConnPerRoute() {
    return maxConnPerRoute();
  }

  public int getMaxConnTotal() {
    return maxConnTotal();
  }


  public RetryProperties getRetry() {
    return retry();
  }
}
