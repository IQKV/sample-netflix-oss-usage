package org.ujar.acmecloudnetflix.userprofile.dto;

import org.ujar.acmecloudnetflix.userprofile.client.dto.DepartmentResponse;


public record UserProfileWithDepartmentDto(String email, String firstName, String lastName, Long departmentId,
                                           DepartmentResponse department) {

}
