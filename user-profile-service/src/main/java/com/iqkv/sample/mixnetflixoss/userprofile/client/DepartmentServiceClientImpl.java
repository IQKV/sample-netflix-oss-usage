/*
 * Copyright 2025 IQKV Foundation Team.
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

package com.iqkv.sample.mixnetflixoss.userprofile.client;

import com.iqkv.sample.mixnetflixoss.userprofile.client.dto.DepartmentResponse;
import com.iqkv.sample.mixnetflixoss.userprofile.config.properties.RestfulApiResourcesProperties;
import com.iqkv.sample.mixnetflixoss.userprofile.exception.Downstream;
import com.iqkv.sample.mixnetflixoss.userprofile.exception.DownstreamException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.retry.annotation.Retryable;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

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
