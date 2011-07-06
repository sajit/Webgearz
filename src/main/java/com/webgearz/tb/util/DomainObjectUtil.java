package com.webgearz.tb.util;

import java.util.ArrayList;
import java.util.List;

import com.webgearz.tb.domain.models.DivContent;
import com.webgearz.tb.domain.models.Template;
import com.webgearz.tb.domain.models.User;

public class DomainObjectUtil {
	
	public static User createUser(){
		User user = new User();
		user.setFirstName("mr.T");
		user.setLastName("goady");
		user.setEmail("abc@gmail.com");
		user.setPassword("abc");
		return user;
	}
	
	public static Template createTemplate(){
		Template template = new Template();
		template.setTemplateName("sample");
		List<String> divs = new ArrayList<String>();
		divs.add("div1");
		divs.add("div2");
		
		template.setDivIds(divs);
		return template;
	}
	
	public static DivContent createDivContent(){
		DivContent divContent = new DivContent();
		divContent.setDomainId("www.stepson.co.uk");
		divContent.setDivId("temp1");
		divContent.setDivContents("Diditonthem");
		return divContent;
		
	}

}
