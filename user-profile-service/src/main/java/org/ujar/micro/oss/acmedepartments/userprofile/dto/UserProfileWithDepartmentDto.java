package org.ujar.micro.oss.acmedepartments.userprofile.dto;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.ujar.micro.oss.acmedepartments.userprofile.client.dto.DepartmentResponse;

@SuppressFBWarnings("EI_EXPOSE_REP")
public record UserProfileWithDepartmentDto(String email, String firstName, String lastName, Long departmentId,
                                           DepartmentResponse department) {

}
