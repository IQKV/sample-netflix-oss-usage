package org.ujar.micro.oss.acmedepartments.userprofile.dto;

public record UserProfileDto(String email, boolean active) {
  public boolean isActive() {
    return active();
  }
}
