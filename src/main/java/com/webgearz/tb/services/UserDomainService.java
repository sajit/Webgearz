package com.webgearz.tb.services;

import java.util.List;

import com.webgearz.tb.domain.models.UserDomain;

public interface UserDomainService {
	
	public UserDomain findUserDomainById(final String domainId);
	
	public List<UserDomain> findDomainsByUser(final String userId);

}
