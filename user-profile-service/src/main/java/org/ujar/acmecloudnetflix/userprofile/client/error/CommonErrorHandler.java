package org.ujar.acmecloudnetflix.userprofile.client.error;

import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.http.client.ClientHttpResponse;
import org.ujar.acmecloudnetflix.userprofile.client.dto.CommonErrorResponse;
import org.ujar.acmecloudnetflix.userprofile.exception.Downstream;


public class CommonErrorHandler extends AbstractErrorHandler {

  public CommonErrorHandler(ObjectMapper objectMapper, Downstream downstream) {
    super(objectMapper, downstream);
  }

  @Override
  @SneakyThrows
  protected String fetchErrorMessage(ClientHttpResponse clientHttpResponse) {
    return objectMapper.readValue(clientHttpResponse.getBody(), CommonErrorResponse.class)
        .getErrors()
        .stream()
        .map(CommonErrorResponse.Error::getMessage)
        .collect(Collectors.joining(", "));
  }
}
