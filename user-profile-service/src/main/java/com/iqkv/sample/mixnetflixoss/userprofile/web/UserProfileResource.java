/*
 * Copyright 2025 IQKV Foundation Team.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.iqkv.sample.mixnetflixoss.userprofile.web;

import javax.validation.Valid;

import com.iqkv.sample.mixnetflixoss.starter.mvc.rest.dto.ErrorResponse;
import com.iqkv.sample.mixnetflixoss.starter.mvc.rest.dto.PageRequestDto;
import com.iqkv.sample.mixnetflixoss.userprofile.dto.UserProfileDto;
import com.iqkv.sample.mixnetflixoss.userprofile.dto.UserProfileWithDepartmentDto;
import com.iqkv.sample.mixnetflixoss.userprofile.entity.UserProfile;
import com.iqkv.sample.mixnetflixoss.userprofile.service.UserProfileService;
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
@Tag(name = "User profile resource", description = "API for user profiles management")
@Validated
@RequiredArgsConstructor
class UserProfileResource {

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
  ResponseEntity<UserProfile> create(@RequestBody final UserProfileDto request) {
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
  ResponseEntity<UserProfileWithDepartmentDto> findById(@PathVariable final String id) {
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
  ResponseEntity<Page<UserProfile>> findAll(@ParameterObject @Valid final PageRequestDto request) {
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
  ResponseEntity<UserProfile> update(@PathVariable final String id, @RequestBody final UserProfileDto request) {
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
  HttpStatus delete(@PathVariable final String id) {
    userProfileService.deleteById(id);
    return HttpStatus.OK;
  }

}
