package com.iqkv.incubator.sample.mixnetflixoss.userprofile.client;

import com.iqkv.incubator.sample.mixnetflixoss.userprofile.client.dto.DepartmentResponse;

public interface DepartmentServiceClient {

  DepartmentResponse getDepartment(Long departmentId);
}
