package dev.knowhowto.acmecloudnetflix.starter.restful.web.dto;

import java.util.List;

public record ErrorResponse<M>(List<Error<M>> errors) {
}
