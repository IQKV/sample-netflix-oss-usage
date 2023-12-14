package dev.knowhowto.acmecloudnetflix.userprofile.dto;

import dev.knowhowto.acmecloudnetflix.userprofile.client.dto.DepartmentResponse;


public record UserProfileWithDepartmentDto(String email, String firstName, String lastName, Long departmentId,
                                           DepartmentResponse department) {

}
