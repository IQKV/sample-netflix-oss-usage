package org.ujar.micro.oss.acmedepartments.configserver.archunit;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

@AnalyzeClasses(packages = "org.ujar.micro.oss.acmedepartments.configserver")
public class NamingConventionTest {

  @ArchTest
  private final ArchRule controllersShouldBeSuffixed =
      classes()
          .that().resideInAnyPackage("..controller..", "..web..")
          .and().areAnnotatedWith(Controller.class).or().areAnnotatedWith(RestController.class)
          .should().haveSimpleNameEndingWith("Controller");

  @ArchTest
  private final ArchRule classesNamedControllerShouldBeInControllerPackage =
      classes()
          .that().haveSimpleNameContaining("Controller")
          .or().areAnnotatedWith(Controller.class).or().areAnnotatedWith(RestController.class)
          .should().resideInAnyPackage("..controller..", "..web..");

}
