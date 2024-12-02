package com.iqkv.incubator.sample.mixnetflixoss.starter.mvc.rest.dto;

import java.util.List;

public record ErrorResponse<M>(List<Error<M>> errors) {
}
