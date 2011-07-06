package com.webgearz.tb.controllers;

import java.io.File;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public class StaticPageController {
	
	@RequestMapping(value="/resources/aloha",method=RequestMethod.GET)
	public File getDictFile(){
		
		return null;
	}
	
	

}
