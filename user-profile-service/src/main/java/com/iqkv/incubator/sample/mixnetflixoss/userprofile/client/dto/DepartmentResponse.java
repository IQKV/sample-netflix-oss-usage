package com.iqkv.incubator.sample.mixnetflixoss.userprofile.client.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentResponse {
  private Long id;
  private String departmentName;
  private String departmentAddress;
  private String departmentCode;
}
