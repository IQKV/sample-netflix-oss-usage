package dev.knowhowto.acmecloudnetflix.userprofile.repository;

import dev.knowhowto.acmecloudnetflix.userprofile.entity.UserProfile;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserProfileRepository
    extends MongoRepository<UserProfile, String> {

}
