package org.ujar.micro.oss.acmedepartments.userprofile.client;

import org.ujar.micro.oss.acmedepartments.userprofile.client.dto.DepartmentResponse;

public interface DepartmentServiceClient {

  DepartmentResponse getDepartment(Long departmentId);
}
