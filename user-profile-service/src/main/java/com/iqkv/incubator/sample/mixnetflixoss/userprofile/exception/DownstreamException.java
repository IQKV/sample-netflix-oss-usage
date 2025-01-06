/*
 * Copyright 2024 IQKV.
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

package com.iqkv.incubator.sample.mixnetflixoss.userprofile.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@Data
@EqualsAndHashCode(callSuper = false)
public class DownstreamException extends RuntimeException {

  private static final String MESSAGE_TEMPLATE = "%s error: %s";

  private final Downstream downstream;
  private final HttpStatus responseCode;

  public DownstreamException(Downstream downstream, Throwable cause, HttpStatus responseCode) {
    super(String.format(MESSAGE_TEMPLATE, downstream.getName(), cause.getMessage()), cause);
    this.downstream = downstream;
    this.responseCode = responseCode;
  }

  public DownstreamException(Downstream downstream, String message, HttpStatus responseCode) {
    super(String.format(MESSAGE_TEMPLATE, downstream.getName(), message));
    this.downstream = downstream;
    this.responseCode = responseCode;
  }

  public DownstreamException(Downstream downstream, Throwable cause) {
    super(String.format(MESSAGE_TEMPLATE, downstream.getName(), cause.getMessage()), cause);
    this.downstream = downstream;
    this.responseCode = null;
  }
}
