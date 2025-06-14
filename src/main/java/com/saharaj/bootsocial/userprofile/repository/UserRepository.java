package com.saharaj.bootsocial.userprofile.repository;

import com.saharaj.bootsocial.userprofile.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends MongoRepository<User, Long>{
    Optional <User> findByUserID(long userID);
    Optional <User> findByUserName(String userName);
}
