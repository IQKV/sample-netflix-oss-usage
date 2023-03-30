package org.ujar.acmecloudnetflix.department.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.ujar.acmecloudnetflix.department.entity.Department;

public interface DepartmentRepository
    extends PagingAndSortingRepository<Department, Long>, JpaRepository<Department, Long> {

}
