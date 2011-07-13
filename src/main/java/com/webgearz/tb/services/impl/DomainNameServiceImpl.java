package com.webgearz.tb.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.document.mongodb.MongoTemplate;
import org.springframework.data.document.mongodb.query.Query;
import org.springframework.stereotype.Service;

import com.webgearz.tb.daos.UserDomainDao;
import com.webgearz.tb.domain.models.UserDomain;
import com.webgearz.tb.exceptions.WebgearzException;
import com.webgearz.tb.services.DomainNameService;


@Service("domainService")
public class DomainNameServiceImpl implements DomainNameService{

	
	
	private UserDomainDao userDomainDao;

	@Override
	public UserDomain addDomain(UserDomain userDomain) {
		if(userDomainDao.findDomainByName(userDomain.getDomainName())!=null){
			//DomainNameServiceImpl.registeredDomains.add(userDomain);
			throw new WebgearzException("Cannot have duplicate domain names");
			
		}
		return userDomainDao.save(userDomain);
		
	}

	

	@Override
	public boolean userDomainExists(UserDomain userDomain) {
		return userDomainDao.findDomainByName(userDomain.getDomainName()) != null;

		
	}


	@Autowired
	public void setUserDomainDao(UserDomainDao userDomainDao) {
		this.userDomainDao = userDomainDao;
	}



	public UserDomainDao getUserDomainDao() {
		return userDomainDao;
	}

}
