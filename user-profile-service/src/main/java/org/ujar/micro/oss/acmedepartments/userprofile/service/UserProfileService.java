package org.ujar.micro.oss.acmedepartments.userprofile.service;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.ujar.micro.oss.acmedepartments.userprofile.client.DepartmentServiceClient;
import org.ujar.micro.oss.acmedepartments.userprofile.dto.UserProfileWithDepartmentDto;
import org.ujar.micro.oss.acmedepartments.userprofile.entity.UserProfile;
import org.ujar.micro.oss.acmedepartments.userprofile.repository.UserProfileRepository;

@Service
@AllArgsConstructor
@SuppressFBWarnings("EI_EXPOSE_REP2")
public class UserProfileService {

  private final UserProfileRepository profileRepository;
  private final DepartmentServiceClient departments;

  public UserProfile saveProfile(UserProfile profile) {
    return profileRepository.save(profile);
  }

  public Optional<UserProfileWithDepartmentDto> findById(String id) {
    var profile = profileRepository.findById(id);
    Optional<UserProfileWithDepartmentDto> result;
    if (profile.isPresent()) {
      var p = profile.get();
      var department = departments.getDepartment(p.getDepartmentId());
      result = Optional.of(
          new UserProfileWithDepartmentDto(p.getEmail(), p.getFirstName(), p.getLastName(), p.getDepartmentId(),
              department));
    } else {
      result = Optional.empty();
    }
    return result;
  }

  public Page<UserProfile> findAll(PageRequest pageRequest) {
    return profileRepository.findAll(pageRequest);
  }

  public void deleteById(String id) {
    profileRepository.deleteById(id);
  }
}
