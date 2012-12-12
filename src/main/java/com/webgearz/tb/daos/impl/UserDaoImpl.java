package com.webgearz.tb.daos.impl;

import java.util.List;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.mongodb.WriteResult;
import com.webgearz.tb.daos.UserDao;
import com.webgearz.tb.domain.models.User;

@Component("userDao")
public class UserDaoImpl extends AbstractMongoDao<User> implements UserDao{

	@Override
	public void addUserDomain(User user, String domainName) {
//		
//		//List<String> userDomains = user.getUserDomains();
//		
//		userDomains.add(domainName);
//		//user.setUserDomains(userDomains);
//		
//		WriteResult wr = mongoOperation.updateFirst(
//				new Query(Criteria.where("_id").is(user.getId())),
//				Update.update("userDomains", userDomains),persistentClass.getSimpleName());
//		System.out.println(wr.getError());
		
	}

	

}
