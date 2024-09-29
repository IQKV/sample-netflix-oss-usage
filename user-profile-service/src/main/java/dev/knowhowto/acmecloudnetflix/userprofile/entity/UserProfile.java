package dev.knowhowto.acmecloudnetflix.userprofile.entity;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = UserProfile.COLLECTION_NAME)
public class UserProfile {

  protected static final String COLLECTION_NAME = "user_profiles";

  @Id
  private String id;

  @Email
  @Size(min = 5, max = 254)
  private String email;

  private String firstName;

  private String lastName;

  private Long departmentId;

  @Override
  public String toString() {
    return "UserProfile{" +
           "id='" + id + '\'' +
           ", email='" + email + '\'' +
           ", firstName='" + firstName + '\'' +
           ", lastName='" + lastName + '\'' +
           ", departmentId=" + departmentId +
           '}';
  }
}
