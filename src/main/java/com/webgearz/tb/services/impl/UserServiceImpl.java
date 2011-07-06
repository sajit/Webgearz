package com.webgearz.tb.services.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.webgearz.tb.domain.models.User;
import com.webgearz.tb.domain.models.UserDomain;
import com.webgearz.tb.mongo.repositories.UserRepository;
import com.webgearz.tb.services.DomainNameService;
import com.webgearz.tb.services.TemplateService;
import com.webgearz.tb.services.UserService;

@Repository("userService")
public class UserServiceImpl implements UserService{
	
	private static final Log log = LogFactory.getLog(UserServiceImpl.class);
	private UserRepository userRepository;
	
	private TemplateService templateService;
	
	private DomainNameService domainNameService;
	
	@Override
	public User store(User user) {
		userRepository.save(user);
		return user;
	}

	
	@Override
	public List<User> getAll() {
		return userRepository.findAll();
	}
	
	@Override
	public User findUser(String userId){
		
		return userRepository.findOne(userId);
	}

	@Autowired
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public UserRepository getUserRepository() {
		return userRepository;
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
		
		List<User> allUsers = userRepository.findAll();
		for(User user : allUsers){
			if(user.getEmail().equals(email)&& user.getPassword().equals(password))
				return user;
		}
		return null;
	}

	@Override
	public boolean addDomains(User storedUser, UserDomain userdomain) {
		if(!domainNameService.userDomainExists(userdomain)){
			
			UserDomain storedUserDomain = domainNameService.addDomain(userdomain);
			Assert.notNull(storedUserDomain);
			log.debug("Stored User Domain " + storedUserDomain.getId());
			storedUser.getUserDomains().add(storedUserDomain);
			userRepository.save(storedUser);
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



}
