package com.iqkv.incubator.sample.mixnetflixoss.userprofile.dto;

import com.iqkv.incubator.sample.mixnetflixoss.userprofile.client.dto.DepartmentResponse;


public record UserProfileWithDepartmentDto(String email, String firstName, String lastName, Long departmentId,
                                           DepartmentResponse department) {

}
