package com.oxd.springbootmongodb.repositories;

import com.oxd.springbootmongodb.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
}
