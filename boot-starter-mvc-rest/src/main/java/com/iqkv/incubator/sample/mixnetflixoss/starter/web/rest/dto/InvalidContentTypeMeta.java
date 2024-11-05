package com.iqkv.incubator.sample.mixnetflixoss.starter.web.rest.dto;

import java.util.ArrayList;
import java.util.List;

public record InvalidContentTypeMeta(String invalidType, List<String> supportedTypes) {

  public List<String> getSupportedTypes() {
    return new ArrayList<>(supportedTypes);
  }
}
