package dev.knowhowto.acmecloudnetflix.userprofile.client;

import dev.knowhowto.acmecloudnetflix.userprofile.client.dto.DepartmentResponse;

public interface DepartmentServiceClient {

  DepartmentResponse getDepartment(Long departmentId);
}
