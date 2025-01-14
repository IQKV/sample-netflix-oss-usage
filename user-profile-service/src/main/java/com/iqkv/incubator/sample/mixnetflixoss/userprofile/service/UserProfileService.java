/*
 * Copyright 2024 IQKV Team.
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

package com.iqkv.incubator.sample.mixnetflixoss.userprofile.service;

import java.util.Optional;

import com.iqkv.incubator.sample.mixnetflixoss.userprofile.client.DepartmentServiceClient;
import com.iqkv.incubator.sample.mixnetflixoss.userprofile.dto.UserProfileWithDepartmentDto;
import com.iqkv.incubator.sample.mixnetflixoss.userprofile.entity.UserProfile;
import com.iqkv.incubator.sample.mixnetflixoss.userprofile.repository.UserProfileRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserProfileService {

  private final UserProfileRepository profileRepository;
  private final DepartmentServiceClient departments;

  public UserProfile saveProfile(final UserProfile profile) {
    return profileRepository.save(profile);
  }

  public Optional<UserProfileWithDepartmentDto> findById(final String id) {
    final var profile = profileRepository.findById(id);
    Optional<UserProfileWithDepartmentDto> result;
    if (profile.isPresent()) {
      final var p = profile.get();
      final var department = departments.getDepartment(p.getDepartmentId());
      result = Optional.of(
          new UserProfileWithDepartmentDto(p.getEmail(), p.getFirstName(), p.getLastName(), p.getDepartmentId(),
              department));
    } else {
      result = Optional.empty();
    }
    return result;
  }

  public Page<UserProfile> findAll(final PageRequest pageRequest) {
    return profileRepository.findAll(pageRequest);
  }

  public void deleteById(final String id) {
    profileRepository.deleteById(id);
  }
}
