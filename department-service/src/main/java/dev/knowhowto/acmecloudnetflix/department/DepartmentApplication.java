package dev.knowhowto.acmecloudnetflix.department;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DepartmentApplication {

  private DepartmentApplication() {
  }

  public static void main(String[] args) {
    SpringApplication.run(DepartmentApplication.class, args);
  }
}
