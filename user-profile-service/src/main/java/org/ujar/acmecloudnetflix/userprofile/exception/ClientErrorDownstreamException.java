package org.ujar.acmecloudnetflix.userprofile.exception;

import lombok.EqualsAndHashCode;
import lombok.Value;
import org.springframework.http.HttpStatus;

@Value
@EqualsAndHashCode(callSuper = true)
public class ClientErrorDownstreamException extends DownstreamException {
  public ClientErrorDownstreamException(Downstream downstream, Throwable cause) {
    super(downstream, cause, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  public ClientErrorDownstreamException(Downstream downstream, String message, HttpStatus responseCode) {
    super(downstream, message, responseCode);
  }
}
