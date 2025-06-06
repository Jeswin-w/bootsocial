package com.saharaj.bootsocial.userprofile.repository;

import com.saharaj.bootsocial.userprofile.entity.AppUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends MongoRepository<AppUser, Long>{
    Optional <AppUser> findByUserID(long userID);
    Optional <AppUser> findByUsername(String username);
}
