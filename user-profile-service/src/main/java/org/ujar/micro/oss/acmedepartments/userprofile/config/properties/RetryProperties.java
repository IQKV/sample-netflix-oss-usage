package org.ujar.micro.oss.acmedepartments.userprofile.config.properties;

import lombok.Data;

@Data
public class RetryProperties {
  private int maxAttempts;
  private long delay;
  private double multiplier;
  private long maxDelay;
}
