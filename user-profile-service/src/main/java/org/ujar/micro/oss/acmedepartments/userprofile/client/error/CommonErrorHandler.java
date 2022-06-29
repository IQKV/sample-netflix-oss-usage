package org.ujar.micro.oss.acmedepartments.userprofile.client.error;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.stream.Collectors;
import lombok.SneakyThrows;
import org.springframework.http.client.ClientHttpResponse;
import org.ujar.micro.oss.acmedepartments.userprofile.client.dto.CommonErrorResponse;
import org.ujar.micro.oss.acmedepartments.userprofile.exception.Downstream;


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
