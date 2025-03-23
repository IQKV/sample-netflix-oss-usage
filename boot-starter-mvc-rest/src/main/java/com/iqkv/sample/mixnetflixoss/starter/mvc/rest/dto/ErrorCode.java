/*
 * Copyright 2025 IQKV Team.
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

package com.iqkv.sample.mixnetflixoss.starter.mvc.rest.dto;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ErrorCode {
  // 1xx represents validation errors
  public static final String REQUEST_BODY_INVALID = "101";
  public static final String REQUEST_PARAMETER_INVALID = "102";
  // 2xx represents bad web requests errors
  public static final String INVALID_HTTP_METHOD = "201";
  public static final String INVALID_REQUEST_CONTENT_TYPE = "202";
  // 3xx represents internal server error
  public static final String UNKNOWN_ERROR = "301";

}
