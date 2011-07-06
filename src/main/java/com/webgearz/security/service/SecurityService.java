package com.webgearz.security.service;

import org.springframework.security.core.userdetails.UserDetails;

import com.webgearz.tb.domain.models.User;

public interface SecurityService {
	
	UserDetails getUserDetailsObject(String userEmail);
	
	User getLoggedInUser();
	
	void addAuthenticatedUser(User user);
	
	

}
