package org.ujar.micro.oss.acmedepartments.userprofile.client.dto;

import java.util.List;
import lombok.Value;

@Value
public class CommonErrorResponse {
  List<Error> errors;

  @Value
  public static class Error {
    String message;
  }
}
