package com.webgearz.tb.services.impl;

import javax.annotation.Resource;

import junit.framework.Assert;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Before;
import org.junit.Test;
import org.springframework.data.document.mongodb.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.webgearz.tb.daos.UserDomainDao;
import com.webgearz.tb.domain.models.User;
import com.webgearz.tb.domain.models.UserDomain;
import com.webgearz.tb.services.UserDomainService;
import com.webgearz.tb.services.UserService;
import com.webgearz.tb.util.DomainObjectUtil;

@ContextConfiguration(locations={
	    "classpath:test-context.xml"})
public class UserServiceImplTest extends AbstractJUnit4SpringContextTests
{
	private static final Log log = LogFactory.getLog(UserServiceImplTest.class);
	@Resource
	private UserService userService;

	
	private final static String USER_COLLECTION = "user";
	
	@Resource
	private UserDomainDao userDomainDao;
	
	
	@Resource
	private MongoTemplate mongoTemplate;
	
	@Resource
	private UserDomainService userDomainService;
	
	@Before
	public void setup(){
		if(mongoTemplate.collectionExists(User.class.getSimpleName()))
			mongoTemplate.dropCollection(User.class.getSimpleName());
		if(mongoTemplate.collectionExists(UserDomain.class.getSimpleName())){
			mongoTemplate.dropCollection(UserDomain.class.getSimpleName());
		}
		
		mongoTemplate.createCollection(User.class.getSimpleName());
		mongoTemplate.createCollection(UserDomain.class.getSimpleName());
		
		
		
	}
	
	@Test
	public void testStore(){
		int initialCount = userService.getAll().size();
		User storedUser = userService.store(DomainObjectUtil.createUser());
		int finalCount = userService.getAll().size();
		
		Assert.assertEquals(1+initialCount, finalCount);
		
		User dbUser = userService.findUser(storedUser.getId());
		Assert.assertEquals(storedUser.getEmail(), dbUser.getEmail());
	}
	
	@Test
	public void testAddDomainUnique(){
		
		User storedUser = userService.store(DomainObjectUtil.createUser());
		Assert.assertTrue(userService.addDomains(storedUser, new UserDomain("domain1","template1")));
		Assert.assertFalse(userService.addDomains(storedUser, new UserDomain("domain1","template2")));
		
		
	}
	
	
	
	@Test
	public void testAuthenticate(){
		User storedUser = userService.store(DomainObjectUtil.createUser());
		String userEmail = storedUser.getEmail();
		String password = storedUser.getPassword();
		
		User dbUser = userService.authenticate(userEmail, password);
		
		Assert.assertNotNull(dbUser);
	
	}
	
	@Test
	public void userDomainIdsAreSame(){
		User storedUser = userService.store(DomainObjectUtil.createUser());
		userService.addDomains(storedUser, new UserDomain("domain1","template1"));
		User retrievedUser = userService.findUser(storedUser.getId());
		Assert.assertEquals(1, retrievedUser.getUserDomains().size());
		UserDomain userDomain = userDomainDao.findDomainByName("domain1");
		Assert.assertNotNull(userDomain);
		Assert.assertEquals(retrievedUser.getUserDomains().get(0).getId(), userDomain.getId());
		
	}
	
	@Test
	public void testFindByIdUserDomain(){
		User storedUser = userService.store(DomainObjectUtil.createUser());
		userService.addDomains(storedUser, new UserDomain("domain1","template1"));
		User retrievedUser = userService.findUser(storedUser.getId());
		String domainId = retrievedUser.getUserDomains().get(0).getId();
		Assert.assertNotNull(userDomainService.findUserDomainById(domainId));
	}


	
	
}
