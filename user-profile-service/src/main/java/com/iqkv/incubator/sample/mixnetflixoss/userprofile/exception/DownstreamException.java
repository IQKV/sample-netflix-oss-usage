package com.iqkv.incubator.sample.mixnetflixoss.userprofile.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@Data
@EqualsAndHashCode(callSuper = false)
public class DownstreamException extends RuntimeException {

  private static final String MESSAGE_TEMPLATE = "%s error: %s";

  private final Downstream downstream;
  private final HttpStatus responseCode;

  public DownstreamException(Downstream downstream, Throwable cause, HttpStatus responseCode) {
    super(String.format(MESSAGE_TEMPLATE, downstream.getName(), cause.getMessage()), cause);
    this.downstream = downstream;
    this.responseCode = responseCode;
  }

  public DownstreamException(Downstream downstream, String message, HttpStatus responseCode) {
    super(String.format(MESSAGE_TEMPLATE, downstream.getName(), message));
    this.downstream = downstream;
    this.responseCode = responseCode;
  }

  public DownstreamException(Downstream downstream, Throwable cause) {
    super(String.format(MESSAGE_TEMPLATE, downstream.getName(), cause.getMessage()), cause);
    this.downstream = downstream;
    this.responseCode = null;
  }
}
