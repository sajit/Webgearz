package com.webgearz.tb.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.document.mongodb.MongoTemplate;
import org.springframework.data.document.mongodb.query.Criteria;
import org.springframework.data.document.mongodb.query.Query;
import org.springframework.stereotype.Service;

import com.webgearz.tb.domain.models.UserDomain;
import com.webgearz.tb.services.UserDomainService;

@Service("userDomainService")
public class UserDomainServiceImpl implements UserDomainService{

	//private UserDomainRepository userDomainRepository;
	private MongoTemplate mongoTemplate;
	@Override
	public UserDomain findUserDomainById(String domainId) {
		Query query = new Query(Criteria.where(UserDomain.ID).is(domainId));
		return mongoTemplate.findOne(UserDomain.COLLECTION_NAME,query, UserDomain.class);
		
	}
	
	@Autowired
	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}
	public MongoTemplate getMongoTemplate() {
		return mongoTemplate;
	}

}
