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
