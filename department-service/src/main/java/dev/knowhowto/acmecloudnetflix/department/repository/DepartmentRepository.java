package dev.knowhowto.acmecloudnetflix.department.repository;

import dev.knowhowto.acmecloudnetflix.department.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface DepartmentRepository
    extends PagingAndSortingRepository<Department, Long>, JpaRepository<Department, Long> {

}
