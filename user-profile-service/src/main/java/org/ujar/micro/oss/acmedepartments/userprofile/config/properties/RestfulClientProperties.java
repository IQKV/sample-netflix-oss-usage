package org.ujar.micro.oss.acmedepartments.userprofile.config.properties;

import java.time.Duration;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "ujar.restful-client")
public class RestfulClientProperties {
  protected Duration connectTimeout;
  protected Duration readTimeout;
  protected int maxConnPerRoute;
  protected int maxConnTotal;
  protected RetryProperties retry;
}
