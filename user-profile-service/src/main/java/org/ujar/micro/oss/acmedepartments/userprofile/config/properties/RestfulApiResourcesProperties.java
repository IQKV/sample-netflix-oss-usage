package org.ujar.micro.oss.acmedepartments.userprofile.config.properties;

import lombok.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@Value
@ConstructorBinding
@ConfigurationProperties(prefix = "ujar.restful-api-resources")
public class RestfulApiResourcesProperties {
  String departmentServiceBaseUrl;
  String departmentServiceUri;
}
