package com.webgearz.tb.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.document.mongodb.MongoTemplate;
import org.springframework.data.document.mongodb.query.Query;
import org.springframework.stereotype.Service;

import com.webgearz.tb.domain.models.UserDomain;
import com.webgearz.tb.services.DomainNameService;

@Scope("singleton")
@Service("domainService")
public class DomainNameServiceImpl implements DomainNameService{

	
	private MongoTemplate mongoTemplate;


	@Override
	public UserDomain addDomain(UserDomain userDomain) {
		if(!mongoTemplate.find(UserDomain.COLLECTION_NAME,new Query(), UserDomain.class).contains(userDomain)){
			//DomainNameServiceImpl.registeredDomains.add(userDomain);
			mongoTemplate.save(UserDomain.COLLECTION_NAME, userDomain);
			
		}
		return userDomain;
		
	}

	@Autowired
	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	public MongoTemplate getMongoTemplate() {
		return mongoTemplate;
	}

	@Override
	public boolean userDomainExists(UserDomain userDomain) {
		
		List<UserDomain> userDomains = mongoTemplate.find(UserDomain.COLLECTION_NAME,new Query(), UserDomain.class);
		return userDomains.contains(userDomain);
		
	}

}
