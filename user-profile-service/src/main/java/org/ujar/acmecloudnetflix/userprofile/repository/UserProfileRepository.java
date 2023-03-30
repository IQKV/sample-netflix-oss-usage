package org.ujar.acmecloudnetflix.userprofile.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.ujar.acmecloudnetflix.userprofile.entity.UserProfile;

public interface UserProfileRepository
    extends MongoRepository<UserProfile, String> {

}
