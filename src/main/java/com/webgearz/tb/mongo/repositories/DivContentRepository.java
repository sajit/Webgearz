package com.webgearz.tb.mongo.repositories;

import org.springframework.data.document.mongodb.repository.MongoRepository;

import com.webgearz.tb.domain.models.DivContent;

public interface DivContentRepository extends MongoRepository<DivContent,String>{

}
