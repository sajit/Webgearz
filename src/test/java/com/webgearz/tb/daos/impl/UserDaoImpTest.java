package com.webgearz.tb.daos.impl;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import junit.framework.Assert;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.mongodb.Mongo;
import com.mongodb.MongoException;
import com.webgearz.tb.daos.UserDao;
import com.webgearz.tb.domain.models.Sample;
import com.webgearz.tb.domain.models.User;
import com.webgearz.tb.domain.models.UserDomain;
@ContextConfiguration(locations={
"classpath:test-context.xml"})
public class UserDaoImpTest extends AbstractJUnit4SpringContextTests{

	@Resource
	private UserDao userDao;
	
	private static MongoTemplate mongoTemplate;
	@Test
	public void testStore(){
		int initialSize = userDao.getAll().size();
		System.out.println("Initial size " + initialSize);
		User model = new User();
		List<String> uds = new ArrayList<String>();
		uds.add("dom1");
		//model.setUserDomains(uds);
		User storeduser = userDao.save(model);
		Assert.assertNotNull(storeduser);
		
		User retUser = userDao.findById(storeduser.getId());
//		Assert.assertEquals(1, retUser.getUserDomains().size());
//		for(String ud : retUser.getUserDomains()){
//			System.out.println(ud);
//		}
		int finalSize = userDao.getAll().size();
		System.out.println("Final size "+ finalSize);
		Assert.assertEquals(1 + initialSize, finalSize);
	}
	
	
	
	@BeforeClass
	public static void dbSetup() throws UnknownHostException, MongoException{
		Mongo mongo = new Mongo("localhost",27017);
		mongoTemplate = new MongoTemplate(mongo,"test-db");
		if(mongoTemplate.collectionExists("User")){
			System.out.println("Dropping User  collection before test");
			mongoTemplate.dropCollection("User");
			
		}
		mongoTemplate.createCollection("User");
	}
}
