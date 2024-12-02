package com.iqkv.incubator.sample.mixnetflixoss.department.web;

import javax.validation.Valid;

import com.iqkv.incubator.sample.mixnetflixoss.department.dto.DepartmentDto;
import com.iqkv.incubator.sample.mixnetflixoss.department.entity.Department;
import com.iqkv.incubator.sample.mixnetflixoss.department.repository.DepartmentRepository;
import com.iqkv.incubator.sample.mixnetflixoss.starter.mvc.rest.dto.ErrorResponse;
import com.iqkv.incubator.sample.mixnetflixoss.starter.mvc.rest.dto.PageRequestDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Department Resource", description = "API for departments management")
@Validated
@RequiredArgsConstructor
class DepartmentResource {

  private final DepartmentRepository profileRepository;

  @PostMapping("/api/v1/departments")
  @Operation(
      description = "Create department.",
      responses = {
          @ApiResponse(responseCode = "201",
                       description = "Success"),
          @ApiResponse(responseCode = "500",
                       description = "Internal error",
                       content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
          @ApiResponse(responseCode = "400",
                       description = "Bad request",
                       content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
      })
  ResponseEntity<Department> create(@RequestBody final DepartmentDto request) {
    final var profile = new Department(null, request.departmentName(),
        request.departmentAddress(), request.departmentCode());
    return new ResponseEntity<>(profileRepository.save(profile), HttpStatus.CREATED);
  }

  @GetMapping("/api/v1/departments/{id}")
  @Operation(
      description = "Retrieve department by id.",
      responses = {
          @ApiResponse(responseCode = "200",
                       description = "Success"),
          @ApiResponse(responseCode = "500",
                       description = "Internal error",
                       content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
          @ApiResponse(responseCode = "400",
                       description = "Bad request",
                       content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
          @ApiResponse(responseCode = "404",
                       description = "Not found",
                       content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
      })
  ResponseEntity<Department> findById(@PathVariable final Long id) {
    return ResponseEntity.of(profileRepository.findById(id));
  }

  @GetMapping("/api/v1/departments")
  @Operation(
      description = "Retrieve all departments (with pagination).",
      responses = {
          @ApiResponse(responseCode = "200",
                       description = "Success"),
          @ApiResponse(responseCode = "500",
                       description = "Internal error",
                       content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
          @ApiResponse(responseCode = "400",
                       description = "Bad request",
                       content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
      })
  ResponseEntity<Page<Department>> findAll(@ParameterObject @Valid final PageRequestDto request) {
    final var pageRequest = PageRequest.of(request.getPage(), request.getSize());
    return new ResponseEntity<>(profileRepository.findAll(pageRequest), HttpStatus.OK);
  }

  @PutMapping("/api/v1/departments/{id}")
  @Operation(
      description = "Update department.",
      responses = {
          @ApiResponse(responseCode = "200",
                       description = "Success"),
          @ApiResponse(responseCode = "500",
                       description = "Internal error",
                       content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
          @ApiResponse(responseCode = "400",
                       description = "Bad request",
                       content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
      })
  ResponseEntity<Department> update(@PathVariable final Long id, @RequestBody final DepartmentDto request) {
    final var profile = new Department(id, request.departmentName(),
        request.departmentAddress(), request.departmentCode());
    return new ResponseEntity<>(profileRepository.save(profile), HttpStatus.OK);
  }

  @DeleteMapping("/api/v1/departments/{id}")
  @Operation(
      description = "Delete department.",
      responses = {
          @ApiResponse(responseCode = "200",
                       description = "Success"),
          @ApiResponse(responseCode = "500",
                       description = "Internal error",
                       content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
          @ApiResponse(responseCode = "400",
                       description = "Bad request",
                       content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
      })
  HttpStatus delete(@PathVariable final Long id) {
    profileRepository.deleteById(id);
    return HttpStatus.OK;
  }

}
