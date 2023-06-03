package org.ujar.acmecloudnetflix.starter.restful.web.dto;

import java.util.ArrayList;
import java.util.List;

public record InvalidHttpMethodMeta(String invalidMethod, List<String> allowedMethods) {
  public List<String> getAllowedMethods() {
    return new ArrayList<>(allowedMethods);
  }
}
