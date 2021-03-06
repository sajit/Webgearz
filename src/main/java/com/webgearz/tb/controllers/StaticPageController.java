package com.webgearz.tb.controllers;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ServletContextAware;


@Controller
public class StaticPageController implements ServletContextAware{
	private static final List<String> supportedLanguages = Arrays.asList("en","fr");
	private static final Log log = LogFactory.getLog(StaticPageController.class);
	
	@RequestMapping(method=RequestMethod.GET,value="/resources/aloha/i18n/{language}.dict")
	@ResponseBody
	public byte[] getDictFile(@PathVariable("language")final String language) throws IOException{
		String relativeFilepath = "resources/aloha/i18n/"+language+".dict";
		log.info("Hits the Controller");
		return doGetFile(language,relativeFilepath);
		
	}
	@RequestMapping(method=RequestMethod.GET,value="/resources/aloha/plugins/com.gentics.aloha.plugins.{pluginName}/i18n/{language}.dict")
	@ResponseBody
	public byte[] getPluginDictFile(@PathVariable("language")final String language,@PathVariable("pluginName")final String pluginName) throws IOException{
		log.info("Hits the plugin Controller endpoint" );
		String relativeFilepath = "resources/aloha/plugins/com.gentics.aloha.plugins."+pluginName+"/i18n/"+language+".dict";
		return doGetFile(language,relativeFilepath);
	}
	
	private byte[] doGetFile(String language,String relativeFilepath) throws IOException{
		if(language==null)
			throw new RuntimeException("Cannot have empty language");
		if(!supportedLanguages.contains(language))
			throw new RuntimeException("Unsupported language");
	
		
		
		
		URL url = context.getResource(relativeFilepath);
		InputStream is = url.openStream();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte buff[] = new byte[512];
		while (is.read(buff, 0, buff.length) > 0) {
			baos.write(buff);
		}
		return baos.toByteArray();
	}

	private ServletContext context;
	@Override
	@Autowired
	public void setServletContext(ServletContext servletContext) {
		this.context = servletContext;
		
	}
	
	

}
