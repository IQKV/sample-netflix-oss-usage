package org.ujar.micro.oss.acmedepartments.userprofile.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.validation.Valid;
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
import org.ujar.boot.starter.restful.web.dto.ErrorResponse;
import org.ujar.boot.starter.restful.web.dto.PageRequestDto;
import org.ujar.micro.oss.acmedepartments.userprofile.dto.UserProfileDto;
import org.ujar.micro.oss.acmedepartments.userprofile.dto.UserProfileWithDepartmentDto;
import org.ujar.micro.oss.acmedepartments.userprofile.entity.UserProfile;
import org.ujar.micro.oss.acmedepartments.userprofile.service.UserProfileService;

@RestController
@Tag(name = "User profile controller", description = "API for user profiles management")
@Validated
@RequiredArgsConstructor
public class UserProfileController {

  private final UserProfileService userProfileService;

  @PostMapping("/api/v1/user-profiles")
  @Operation(
      description = "Create user profile.",
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
  public ResponseEntity<UserProfile> create(@RequestBody UserProfileDto request) {
    final var profile = new UserProfile(null, request.email(),
        request.firstName(), request.lastName(), request.departmentId());
    return new ResponseEntity<>(userProfileService.saveProfile(profile), HttpStatus.CREATED);
  }

  @GetMapping("/api/v1/user-profiles/{id}")
  @Operation(
      description = "Retrieve user profile by id.",
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
  public ResponseEntity<UserProfileWithDepartmentDto> findById(@PathVariable final String id) {
    return ResponseEntity.of(userProfileService.findById(id));
  }

  @GetMapping("/api/v1/user-profiles")
  @Operation(
      description = "Retrieve all user profiles (with pagination).",
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
  public ResponseEntity<Page<UserProfile>> findAll(@ParameterObject @Valid PageRequestDto request) {
    final var pageRequest = PageRequest.of(request.getPage(), request.getSize());
    return new ResponseEntity<>(userProfileService.findAll(pageRequest), HttpStatus.OK);
  }

  @PutMapping("/api/v1/user-profiles/{id}")
  @Operation(
      description = "Update user profile.",
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
  public ResponseEntity<UserProfile> update(@PathVariable final String id, @RequestBody UserProfileDto request) {
    final var profile = new UserProfile(id, request.email(),
        request.firstName(), request.lastName(), request.departmentId());
    return new ResponseEntity<>(userProfileService.saveProfile(profile), HttpStatus.OK);
  }

  @DeleteMapping("/api/v1/user-profiles/{id}")
  @Operation(
      description = "Delete user profile.",
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
  public HttpStatus delete(@PathVariable String id) {
    userProfileService.deleteById(id);
    return HttpStatus.OK;
  }

}
