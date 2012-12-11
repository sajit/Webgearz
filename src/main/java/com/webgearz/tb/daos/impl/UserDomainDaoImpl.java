package com.webgearz.tb.daos.impl;


import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.webgearz.tb.daos.UserDomainDao;
import com.webgearz.tb.domain.models.UserDomain;

@Repository("userDomainDao")
public class UserDomainDaoImpl extends AbstractMongoDao<UserDomain> implements UserDomainDao{

	@Override
	public UserDomain findDomainByName(String domainName) {
		Query query = new Query(Criteria.where(UserDomain.DOMAIN_NAME).is(domainName));
		return mongoTemplate.findOne(query, persistentClass);
	
	}

}
