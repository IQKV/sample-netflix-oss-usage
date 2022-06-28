package org.ujar.micro.oss.acmedepartments.department.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.ujar.micro.oss.acmedepartments.department.entity.Department;

public interface DepartmentRepository
    extends PagingAndSortingRepository<Department, Long>, JpaRepository<Department, Long> {

}
