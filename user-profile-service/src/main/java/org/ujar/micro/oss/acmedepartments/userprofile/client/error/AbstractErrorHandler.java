package org.ujar.micro.oss.acmedepartments.userprofile.client.error;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.nio.charset.Charset;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.StreamUtils;
import org.springframework.web.client.ResponseErrorHandler;
import org.ujar.micro.oss.acmedepartments.userprofile.exception.ClientErrorDownstreamException;
import org.ujar.micro.oss.acmedepartments.userprofile.exception.Downstream;
import org.ujar.micro.oss.acmedepartments.userprofile.exception.DownstreamException;

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
    var statusCode = clientHttpResponse.getStatusCode();
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
