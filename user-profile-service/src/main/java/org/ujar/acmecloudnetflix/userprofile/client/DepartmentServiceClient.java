package org.ujar.acmecloudnetflix.userprofile.client;

import org.ujar.acmecloudnetflix.userprofile.client.dto.DepartmentResponse;

public interface DepartmentServiceClient {

  DepartmentResponse getDepartment(Long departmentId);
}
