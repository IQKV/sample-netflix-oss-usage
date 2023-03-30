package org.ujar.acmecloudnetflix.userprofile.client;

import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.retry.annotation.Retryable;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.ujar.acmecloudnetflix.userprofile.client.dto.DepartmentResponse;
import org.ujar.acmecloudnetflix.userprofile.config.properties.RestfulApiResourcesProperties;
import org.ujar.acmecloudnetflix.userprofile.exception.Downstream;
import org.ujar.acmecloudnetflix.userprofile.exception.DownstreamException;

@RequiredArgsConstructor
public class DepartmentServiceClientImpl implements DepartmentServiceClient {
  private final RestTemplate restTemplate;
  private final RestfulApiResourcesProperties apiResourcesProperties;

  @Override
  @Retryable(interceptor = "departmentServiceRetryInterceptor")
  public DepartmentResponse getDepartment(Long departmentId) {
    try {
      final var builder = UriComponentsBuilder.fromPath(
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
