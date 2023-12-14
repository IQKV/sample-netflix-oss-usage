package dev.knowhowto.acmecloudnetflix.userprofile.client.dto;

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
