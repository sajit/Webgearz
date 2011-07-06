package com.webgearz.tb.mongo.repositories;

import org.springframework.data.document.mongodb.repository.MongoRepository;
import org.springframework.transaction.annotation.Transactional;

import com.webgearz.tb.domain.models.Template;


public interface TemplateRepository extends MongoRepository<Template,String> {

}
