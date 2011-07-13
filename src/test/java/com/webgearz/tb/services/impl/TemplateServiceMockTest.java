package com.webgearz.tb.services.impl;

import static org.mockito.Mockito.when;
import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.webgearz.tb.daos.TemplateDao;
import com.webgearz.tb.domain.models.Template;
import com.webgearz.tb.exceptions.WebgearzException;
import com.webgearz.tb.util.DomainObjectUtil;

public class TemplateServiceMockTest {
	
	TemplateServiceImpl service = new TemplateServiceImpl();
	
	@Mock
	private TemplateDao templateDao;
	@Test
	public void testGetGoodTemplateDivs(){
		Assert.assertFalse(service.getDivsOfTemplate("good").isEmpty());
	}
	
	@Test(expected=WebgearzException.class)
	public void badTemplateThrowsException(){
		service.getDivsOfTemplate("bad");
	}
	
	
	
	@Before
	public void setup(){
		
		MockitoAnnotations.initMocks(this);
		Template good = DomainObjectUtil.createTemplate();
		good.setId("good");
		when(templateDao.findById("good")).thenReturn(good);
		service.setTemplateDao(templateDao);
	}

}
