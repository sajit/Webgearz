package com.webgearz.tb.daos;

import java.util.List;

import com.webgearz.tb.domain.models.UserDomain;

public interface UserDomainDao extends GenericDao<UserDomain>{
	
	UserDomain findDomainByName(String domainName);
	
	List<UserDomain> findDomainByUser(String userId);
	

}
