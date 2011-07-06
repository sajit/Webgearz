package com.webgearz.tb.util;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

@Component("templateFactory")
public class TemplateFactoryImpl implements TemplateFactory{

	private static final String templateBase="templates/";
	

	@Override
	public ModelAndView getTemplate(String templateName,String pageName) {
		ModelAndView mav = new ModelAndView(templateBase+templateName+'/'+pageName);
		return mav;
	}

}
