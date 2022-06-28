package org.ujar.micro.oss.acmedepartments.userprofile.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.ujar.micro.oss.acmedepartments.userprofile.entity.UserProfile;

public interface UserProfileRepository
    extends MongoRepository<UserProfile, String> {

}
