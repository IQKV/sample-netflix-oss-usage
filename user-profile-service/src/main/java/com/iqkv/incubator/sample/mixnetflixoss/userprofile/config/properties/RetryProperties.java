package com.iqkv.incubator.sample.mixnetflixoss.userprofile.config.properties;

import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.validation.annotation.Validated;

@Validated
@ConstructorBinding
public record RetryProperties(
    int maxAttempts,
    long delay,
    double multiplier,
    long maxDelay) {

  public int getMaxAttempts() {
    return maxAttempts();
  }

  public long getDelay() {
    return delay();
  }

  public double getMultiplier() {
    return multiplier();
  }

  public long getMaxDelay() {
    return maxDelay();
  }
}
