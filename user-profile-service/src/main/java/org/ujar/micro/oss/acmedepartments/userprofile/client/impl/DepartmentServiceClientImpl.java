package org.ujar.micro.oss.acmedepartments.userprofile.client.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.retry.annotation.Retryable;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.ujar.micro.oss.acmedepartments.userprofile.client.DepartmentServiceClient;
import org.ujar.micro.oss.acmedepartments.userprofile.client.dto.DepartmentResponse;
import org.ujar.micro.oss.acmedepartments.userprofile.config.properties.RestfulApiResourcesProperties;
import org.ujar.micro.oss.acmedepartments.userprofile.exception.Downstream;
import org.ujar.micro.oss.acmedepartments.userprofile.exception.DownstreamException;

@RequiredArgsConstructor
public class DepartmentServiceClientImpl implements DepartmentServiceClient {
  private final RestTemplate restTemplate;
  private final RestfulApiResourcesProperties apiResourcesProperties;

  @Override
  @Retryable(interceptor = "departmentServiceRetryInterceptor")
  public DepartmentResponse getDepartment(Long departmentId) {
    try {
      var headers = new HttpHeaders();
      var builder = UriComponentsBuilder.fromPath(
          apiResourcesProperties.getDepartmentServiceUri().replace("{id}", departmentId.toString())
      );
      return restTemplate.exchange(builder.toUriString(), HttpMethod.GET, null,
          new ParameterizedTypeReference<DepartmentResponse>() {
          }).getBody();
    } catch (ResourceAccessException e) {
      throw new DownstreamException(Downstream.DEPARTMENT_SERVICE, e, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
