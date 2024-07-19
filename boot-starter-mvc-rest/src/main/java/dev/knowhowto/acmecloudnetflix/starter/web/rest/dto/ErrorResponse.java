package dev.knowhowto.acmecloudnetflix.starter.web.rest.dto;

import java.util.List;

public record ErrorResponse<M>(List<Error<M>> errors) {
}
