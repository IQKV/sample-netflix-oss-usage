package dev.knowhowto.acmecloudnetflix.apigateway.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
record FallbackResource() {

  @GetMapping("/userProfileServiceFallback")
  String userProfileServiceFallbackMethod() {
    return "User Service is taking longer than expected." +
           " Please try again later";
  }

  @GetMapping("/departmentServiceFallback")
  String departmentServiceFallbackMethod() {
    return "Department Service is taking longer than expected." +
           " Please try again later";
  }
}
