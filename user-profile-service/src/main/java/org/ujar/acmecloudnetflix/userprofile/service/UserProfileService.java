package org.ujar.acmecloudnetflix.userprofile.service;

import java.util.Optional;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.ujar.acmecloudnetflix.userprofile.client.DepartmentServiceClient;
import org.ujar.acmecloudnetflix.userprofile.dto.UserProfileWithDepartmentDto;
import org.ujar.acmecloudnetflix.userprofile.entity.UserProfile;
import org.ujar.acmecloudnetflix.userprofile.repository.UserProfileRepository;

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
