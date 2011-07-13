package com.webgearz.tb.services.impl;

import java.util.List;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.data.document.mongodb.MongoTemplate;
import org.springframework.data.document.mongodb.query.Query;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.webgearz.tb.domain.models.UserDomain;
import com.webgearz.tb.exceptions.WebgearzException;
import com.webgearz.tb.services.DomainNameService;

@ContextConfiguration(locations={
"classpath:test-context.xml"})
public class DomainNameServiceTest extends AbstractJUnit4SpringContextTests{
	@Resource
	private DomainNameService domainNameService;
	
	@Resource
	private MongoTemplate mongoTemplate;
	
	@Before
	public void setup(){
		if(mongoTemplate.collectionExists(UserDomain.COLLECTION_NAME))
			mongoTemplate.dropCollection(UserDomain.COLLECTION_NAME);
		mongoTemplate.createCollection(UserDomain.COLLECTION_NAME);
		
		UserDomain d1 = new UserDomain("domain1","template1");
		domainNameService.addDomain(d1);
		
		
	}
	
	@After
	public void cleanup(){
		if(mongoTemplate.collectionExists(UserDomain.COLLECTION_NAME))
			mongoTemplate.dropCollection(UserDomain.COLLECTION_NAME);
		
	}
	@Test
	public void returnsTrueIfdomainExists(){
		
		
		UserDomain d2 = new UserDomain("domain1","template2");
		Assert.assertTrue(domainNameService.userDomainExists(d2));
	}
	
	@Test
	public void returnsFalseIfdomainDoestMatch(){
		UserDomain d2 = new UserDomain("domain2","template1");
		Assert.assertFalse(domainNameService.userDomainExists(d2));
	}
	
	@Test
	public void onlyUniqueDomainsWillBeAdded(){
		List<UserDomain> userDomains = mongoTemplate.find(UserDomain.COLLECTION_NAME,new Query(), UserDomain.class);
		//Assert.assertEquals(1, userDomains.size());
		domainNameService.addDomain(new UserDomain("domain2","template1"));
		
		List<UserDomain> updatedUserDomains = mongoTemplate.find(UserDomain.COLLECTION_NAME,new Query(), UserDomain.class);
		
		Assert.assertEquals(1+userDomains.size(), updatedUserDomains.size());
		
	}
	
	@Test(expected=WebgearzException.class)
	public void sameDomainNamewontbeAdded(){
		
		domainNameService.addDomain(new UserDomain("domain1","template2"));
		
	}
	
	
	

}
