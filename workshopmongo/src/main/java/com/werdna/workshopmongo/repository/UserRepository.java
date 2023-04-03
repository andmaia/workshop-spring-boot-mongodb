package com.werdna.workshopmongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.werdna.workshopmongo.domain.User;

public interface UserRepository extends MongoRepository<User, String> {

}
