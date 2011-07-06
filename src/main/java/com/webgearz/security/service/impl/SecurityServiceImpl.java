package com.webgearz.security.service.impl;


import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.document.mongodb.MongoTemplate;
import org.springframework.data.document.mongodb.query.Criteria;
import org.springframework.data.document.mongodb.query.Query;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.webgearz.security.service.SecurityService;
import com.webgearz.tb.domain.models.LoggedInUser;
import com.webgearz.tb.domain.models.User;


@Service("securityService")
public class SecurityServiceImpl implements SecurityService{

	private MongoTemplate mongoTemplate;
	private static final Log log = LogFactory.getLog(SecurityServiceImpl.class);
	@Resource
	private ProviderManager authenticationManager;

	
	@Override
	public UserDetails getUserDetailsObject(String userEmail) {
		Assert.notNull(mongoTemplate);
		Query query = new Query(Criteria.where(User.EMAIL).is(userEmail));
		User user = mongoTemplate.findOne(User.COLLECTION_NAME, query, User.class);
		log.debug("Email --> " + userEmail + ", User --> " + user );
		if(user==null)
			return null;
		return new LoggedInUser(user);
	}

	@Autowired
	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	public MongoTemplate getMongoTemplate() {
		return mongoTemplate;
	}

	@Override
	public User getLoggedInUser() {
		throw new UnsupportedOperationException("Watch the video : http://www.infoq.com/presentations/Spring-Security-3 about usercontextService");
	}

	@Override
	public void addAuthenticatedUser(User user) {
		LoggedInUser principal = (LoggedInUser)getUserDetailsObject(user.getEmail());
		
		Assert.isTrue(!principal.getGrantedAuthorities().isEmpty());
		
		@SuppressWarnings("deprecation")
		Authentication authentication = new UsernamePasswordAuthenticationToken(principal,principal.getPassword(),principal.getGrantedAuthorities().toArray(new GrantedAuthority[principal.getGrantedAuthorities().size()]));
		authenticationManager.authenticate(authentication);
		log.debug("Authenticated new user using authentication manager " + "Set securityContext");
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		
	}

}
