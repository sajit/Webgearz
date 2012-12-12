package com.webgearz.tb.services;

import com.webgearz.tb.domain.models.UserDomain;

public interface DomainNameService {
	
	public boolean userDomainExists(UserDomain userDomain);
	public UserDomain addDomain(UserDomain userDomain);
	
	public UserDomain findDomainByName(String domainName);

}
