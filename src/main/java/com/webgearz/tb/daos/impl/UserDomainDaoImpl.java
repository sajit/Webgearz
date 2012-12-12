package com.webgearz.tb.daos.impl;


import java.util.List;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.webgearz.tb.daos.UserDomainDao;
import com.webgearz.tb.domain.models.UserDomain;

@Component("userDomainDao")
public class UserDomainDaoImpl extends AbstractMongoDao<UserDomain> implements UserDomainDao{

	@Override
	public UserDomain findDomainByName(String domainName) {
		Query query = new Query(Criteria.where(UserDomain.DOMAIN_NAME).is(domainName));
		//mongoOperation.findOne(query, entityClass, collectionName)
		return mongoOperation.findOne(query, persistentClass,persistentClass.getSimpleName());
	
	}

	@Override
	public List<UserDomain> findDomainByUser(String userId) {
		Query query = new Query(Criteria.where("userId").is(userId));
		return mongoOperation.find(query, persistentClass, persistentClass.getSimpleName());
		
	}

}
