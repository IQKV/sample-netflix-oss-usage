package dev.knowhowto.acmecloudnetflix.userprofile;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@SuppressWarnings({"PMD.ClassNamingConventions", "PMD.UnusedPrivateMethod"})
class UserProfileServiceApplicationIT {

  @Test
  void contextLoad() {
    Assertions.assertDoesNotThrow(this::doNotThrowException);
  }

  private void doNotThrowException() {
    // This method will never throw exception
  }
}
