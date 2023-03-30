package org.ujar.acmecloudnetflix.department.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = Department.TABLE_NAME)
public class Department {

  protected static final String TABLE_NAME = "departments";

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String departmentName;
  private String departmentAddress;
  private String departmentCode;

  @Override
  public String toString() {
    return "Department{" +
           "id=" + id +
           ", departmentName='" + departmentName + '\'' +
           ", departmentAddress='" + departmentAddress + '\'' +
           ", departmentCode='" + departmentCode + '\'' +
           '}';
  }
}
