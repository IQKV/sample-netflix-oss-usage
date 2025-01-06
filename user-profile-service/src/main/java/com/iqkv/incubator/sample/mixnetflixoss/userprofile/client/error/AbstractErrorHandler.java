/*
 * Copyright 2024 IQKV.
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

package com.iqkv.incubator.sample.mixnetflixoss.userprofile.client.error;

import java.nio.charset.Charset;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iqkv.incubator.sample.mixnetflixoss.userprofile.exception.ClientErrorDownstreamException;
import com.iqkv.incubator.sample.mixnetflixoss.userprofile.exception.Downstream;
import com.iqkv.incubator.sample.mixnetflixoss.userprofile.exception.DownstreamException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.StreamUtils;
import org.springframework.web.client.ResponseErrorHandler;

@Slf4j
@RequiredArgsConstructor
public abstract class AbstractErrorHandler implements ResponseErrorHandler {

  protected final ObjectMapper objectMapper;
  protected final Downstream downstream;

  @Override
  @SneakyThrows
  public boolean hasError(ClientHttpResponse clientHttpResponse) {
    return clientHttpResponse.getStatusCode().isError();
  }

  @Override
  @SneakyThrows
  public void handleError(ClientHttpResponse clientHttpResponse) {
    final var statusCode = clientHttpResponse.getStatusCode();
    String errorMessage = "";
    try {
      errorMessage = fetchErrorMessage(clientHttpResponse);
    } catch (Exception exception) {
      log.warn("Unable to parse error body", exception);
      errorMessage = StreamUtils.copyToString(clientHttpResponse.getBody(), Charset.defaultCharset());
    }
    if (statusCode.is4xxClientError()) {
      throw new ClientErrorDownstreamException(downstream, errorMessage, statusCode);
    }
    throw new DownstreamException(downstream, errorMessage, statusCode);
  }

  protected abstract String fetchErrorMessage(ClientHttpResponse clientHttpResponse);
}
