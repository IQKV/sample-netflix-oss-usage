package org.ujar.micro.oss.acmedepartments.userprofile.dto;

import org.ujar.micro.oss.acmedepartments.userprofile.client.dto.DepartmentResponse;

public record UserProfileWithDepartmentDto(String email, String firstName, String lastName, Long departmentId,
                                           DepartmentResponse department) {

}
