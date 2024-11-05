package com.iqkv.incubator.sample.mixnetflixoss.department.repository;

import com.iqkv.incubator.sample.mixnetflixoss.department.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface DepartmentRepository
    extends PagingAndSortingRepository<Department, Long>, JpaRepository<Department, Long> {

}
