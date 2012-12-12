package com.webgearz.tb.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.webgearz.tb.daos.UserDomainDao;
import com.webgearz.tb.domain.models.UserDomain;
import com.webgearz.tb.services.UserDomainService;

@Service("userDomainService")
public class UserDomainServiceImpl implements UserDomainService{

	private UserDomainDao userDomainDao;
	@Override
	public UserDomain findUserDomainById(String domainId) {
		return userDomainDao.findById(domainId);
		
	}
	@Autowired
	public void setUserDomainDao(@Qualifier("userDomainDao")UserDomainDao userDomainDao) {
		this.userDomainDao = userDomainDao;
	}
	public UserDomainDao getUserDomainDao() {
		return userDomainDao;
	}
	@Override
	public List<UserDomain> findDomainsByUser(String userId) {
		return userDomainDao.findDomainByUser(userId);
	}
	

}
