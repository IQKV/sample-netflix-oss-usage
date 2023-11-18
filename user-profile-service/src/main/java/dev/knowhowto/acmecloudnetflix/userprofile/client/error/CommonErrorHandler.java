package dev.knowhowto.acmecloudnetflix.userprofile.client.error;

import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.knowhowto.acmecloudnetflix.userprofile.client.dto.CommonErrorResponse;
import dev.knowhowto.acmecloudnetflix.userprofile.exception.Downstream;
import lombok.SneakyThrows;
import org.springframework.http.client.ClientHttpResponse;


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
