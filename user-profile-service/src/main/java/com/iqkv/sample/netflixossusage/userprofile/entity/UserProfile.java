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

package com.iqkv.sample.netflixossusage.userprofile.entity;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = UserProfile.COLLECTION_NAME)
public class UserProfile {

  protected static final String COLLECTION_NAME = "user_profiles";

  @Id
  private String id;

  @Email
  @Size(min = 5, max = 254)
  private String email;

  private String firstName;

  private String lastName;

  private Long departmentId;

  @Override
  public String toString() {
    return "UserProfile{" +
           "id='" + id + '\'' +
           ", email='" + email + '\'' +
           ", firstName='" + firstName + '\'' +
           ", lastName='" + lastName + '\'' +
           ", departmentId=" + departmentId +
           '}';
  }
}
