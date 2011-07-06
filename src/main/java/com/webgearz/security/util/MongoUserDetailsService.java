package com.webgearz.security.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.webgearz.security.service.SecurityService;



@Service("userDetailsService")
public class MongoUserDetailsService implements UserDetailsService{

	private static final Log log = LogFactory.getLog(MongoUserDetailsService.class);
	private SecurityService securityService;
	@Override
	public UserDetails loadUserByUsername(String userEmail)
			throws UsernameNotFoundException, DataAccessException {
		log.debug("Mongo User Details Service ");
		UserDetails userDetails = securityService.getUserDetailsObject(userEmail);
		if(userDetails==null)
			throw new UsernameNotFoundException("User not found");
		log.debug(userDetails);
		
		return userDetails;
	}
	


	@Autowired
	public void setSecurityService(SecurityService securityService) {
		this.securityService = securityService;
	}

	public SecurityService getSecurityService() {
		return securityService;
	}

}
