package com.webgearz.tb.mongo.repositories;

import org.springframework.data.document.mongodb.repository.MongoRepository;

import com.webgearz.tb.domain.models.UserDomain;

public interface UserDomainRepository extends MongoRepository<UserDomain,String>{

}
