package com.iqkv.incubator.sample.mixnetflixoss.userprofile.repository;

import com.iqkv.incubator.sample.mixnetflixoss.userprofile.entity.UserProfile;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserProfileRepository
    extends MongoRepository<UserProfile, String> {

}
