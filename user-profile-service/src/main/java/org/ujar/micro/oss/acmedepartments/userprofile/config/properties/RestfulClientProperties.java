package org.ujar.micro.oss.acmedepartments.userprofile.config.properties;

import java.time.Duration;
import lombok.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@Value
@ConstructorBinding
@ConfigurationProperties(prefix = "ujar.restful-client")
public class RestfulClientProperties {
  Duration connectTimeout;
  Duration readTimeout;
  int maxConnPerRoute;
  int maxConnTotal;
  RetryProperties retry;
}
