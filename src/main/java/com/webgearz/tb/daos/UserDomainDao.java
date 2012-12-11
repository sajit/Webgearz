package com.webgearz.tb.daos;

import com.webgearz.tb.domain.models.UserDomain;

public interface UserDomainDao extends GenericDao<UserDomain>{
	
	UserDomain findDomainByName(String domainName);
	

}
