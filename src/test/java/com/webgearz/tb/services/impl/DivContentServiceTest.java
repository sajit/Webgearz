package com.webgearz.tb.services.impl;

import java.net.UnknownHostException;

import javax.annotation.Resource;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.data.document.mongodb.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.mongodb.Mongo;
import com.mongodb.MongoException;
import com.webgearz.tb.domain.models.DivContent;
import com.webgearz.tb.services.DivContentService;
import com.webgearz.tb.util.DomainObjectUtil;

@ContextConfiguration(locations={
"classpath:test-context.xml"})
public class DivContentServiceTest extends AbstractJUnit4SpringContextTests{
	
	@Resource
	private DivContentService divContentService;
	
	
	private static MongoTemplate mongoTemplate;
	
	private DivContent divContent;
	private DivContent storedDivContent;
	
	@Before
	public void setup(){
		
		divContent = DomainObjectUtil.createDivContent();
		storedDivContent = divContentService.storeDivContent(divContent);
		
	}
	@Test
	public void testGetDivContents(){
		String divContentValue = divContentService.getDivContent(divContent.getDomainId(), divContent.getDivId());
		Assert.assertEquals(divContent.getDivContents(), divContentValue);
	}
	
	@Test
	public void noDivShouldReturnDefault(){
		String divContentValue = divContentService.getDivContent(divContent.getDomainId(), null);
		Assert.assertEquals("Default text", divContentValue);
	}
	
	@Test
	public void testGetById(){
		Assert.assertNotNull(divContentService.getDivContentById(storedDivContent.getId()));
	}
	
	@BeforeClass
	public static void dbSetup() throws UnknownHostException, MongoException{
		Mongo mongo = new Mongo("localhost",27017);
		mongoTemplate = new MongoTemplate(mongo,"mydb1");
		if(mongoTemplate.collectionExists("DivContent")){
			System.out.println("Dropping DivContent  collection before test");
			mongoTemplate.dropCollection("DivContent");
			
		}
		mongoTemplate.createCollection("DivContent");
	}
	

}
