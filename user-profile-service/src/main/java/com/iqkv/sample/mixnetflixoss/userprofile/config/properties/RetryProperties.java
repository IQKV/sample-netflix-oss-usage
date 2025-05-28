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

package com.iqkv.sample.mixnetflixoss.userprofile.config.properties;

import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.validation.annotation.Validated;

@Validated
@ConstructorBinding
public record RetryProperties(
    int maxAttempts,
    long delay,
    double multiplier,
    long maxDelay) {

  public int getMaxAttempts() {
    return maxAttempts();
  }

  public long getDelay() {
    return delay();
  }

  public double getMultiplier() {
    return multiplier();
  }

  public long getMaxDelay() {
    return maxDelay();
  }
}
