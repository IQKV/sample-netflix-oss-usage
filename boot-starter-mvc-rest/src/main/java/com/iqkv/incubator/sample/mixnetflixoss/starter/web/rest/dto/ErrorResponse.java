package com.iqkv.incubator.sample.mixnetflixoss.starter.web.rest.dto;

import java.util.List;

public record ErrorResponse<M>(List<Error<M>> errors) {
}
