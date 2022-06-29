package org.ujar.micro.oss.acmedepartments.userprofile.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "ujar.restful-api-resources")
public class RestfulApiResourcesProperties {
  private String departmentServiceBaseUrl;
  private String departmentServiceUri;
}
