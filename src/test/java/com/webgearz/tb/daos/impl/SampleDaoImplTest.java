package com.webgearz.tb.daos.impl;

import java.net.UnknownHostException;

import javax.annotation.Resource;

import junit.framework.Assert;

import org.junit.BeforeClass;
import org.junit.Test;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.mongodb.Mongo;
import com.mongodb.MongoException;
import com.webgearz.tb.daos.SampleDao;
import com.webgearz.tb.domain.models.Sample;
@ContextConfiguration(locations={
"classpath:test-context.xml"})
public class SampleDaoImplTest extends AbstractJUnit4SpringContextTests{
	
	@Resource
	private SampleDao sampleDao;
	
	
	private static MongoTemplate mongoTemplate;
	@Test
	public void testStore(){
		int initialSize = sampleDao.getAll().size();
		System.out.println("Initial size " + initialSize);
		Sample model = new Sample();
		model.setTestName("huiting");
		Sample storedSample = sampleDao.save(model);
		Assert.assertNotNull(storedSample);
		Assert.assertNotNull(storedSample.getId());
		int finalSize = sampleDao.getAll().size();
		System.out.println("Final size "+ finalSize);
		Assert.assertEquals(1 + initialSize, finalSize);
	}
	
	@Test
	public void testUniqueGet(){
		Sample model = new Sample();
		Sample storedSample = sampleDao.save(model);
		//System.out.println("Stored Sample Id" + storedSample.getId().toString());
		//Sample retrievedSample = sampleDao.findById(storedSample.getId());
		Assert.assertNotNull(sampleDao.findById(storedSample.getId()));
	}
	
	
	
	@BeforeClass
	public static void dbSetup() throws UnknownHostException, MongoException{
		Mongo mongo = new Mongo("localhost",27017);
		mongoTemplate = new MongoTemplate(mongo,"mydb1");
		if(mongoTemplate.collectionExists("Sample")){
			System.out.println("Dropping Sample  collection before test");
			mongoTemplate.dropCollection("Sample");
			
		}
		mongoTemplate.createCollection("Sample");
	}

}
