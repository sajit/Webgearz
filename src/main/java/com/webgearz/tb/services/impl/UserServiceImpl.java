package com.webgearz.tb.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.webgearz.tb.daos.UserDao;
import com.webgearz.tb.domain.models.User;
import com.webgearz.tb.domain.models.UserDomain;
import com.webgearz.tb.services.DomainNameService;
import com.webgearz.tb.services.TemplateService;
import com.webgearz.tb.services.UserService;

@Service("userService")
public class UserServiceImpl implements UserService{
	
	private static final Log log = LogFactory.getLog(UserServiceImpl.class);
	private UserDao userDao;
	
	private TemplateService templateService;
	
	private DomainNameService domainNameService;
	
	@Override
	public User store(User user) {
		return userDao.save(user);
	}

	
	@Override
	public List<User> getAll() {
		return userDao.getAll();
	}
	
	@Override
	public User findUser(String userId){
		
		return userDao.findById(userId);
	}





	@Autowired
	public void setTemplateService(TemplateService templateService) {
		this.templateService = templateService;
	}

	public TemplateService getTemplateService() {
		return templateService;
	}

	@Override
	public User authenticate(String email, String password) {
		
		List<User> allUsers = userDao.getAll();
		for(User user : allUsers){
			if(user.getEmail().equals(email)&& user.getPassword().equals(password))
				return user;
		}
		return null;
	}

	@Override
	public boolean addDomains(User storedUser, UserDomain userdomain) {
		if(!domainNameService.userDomainExists(userdomain)){
			userdomain.setUserId(storedUser.getId());
			UserDomain storedUserDomain = domainNameService.addDomain(userdomain);
			Assert.notNull(storedUserDomain);
			log.debug("Stored User Domain " + storedUserDomain.getId());
			//userDao.addUserDomain(storedUser, storedUserDomain.getDomainName());
			return true;
		}
		return false;
	}


	@Autowired
	public void setDomainNameService(DomainNameService domainNameService) {
		this.domainNameService = domainNameService;
	}


	public DomainNameService getDomainNameService() {
		return domainNameService;
	}


	@Autowired
	public void setUserDao(@Qualifier("userDao")UserDao userDao) {
		this.userDao = userDao;
	}


	public UserDao getUserDao() {
		return userDao;
	}



}
