package com.webgearz.tb.mongo.repositories;

import org.springframework.data.document.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.webgearz.tb.domain.models.User;


public interface UserRepository extends MongoRepository<User,String>{

}
