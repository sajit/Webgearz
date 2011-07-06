package com.webgearz.tb.util;

import org.springframework.web.servlet.ModelAndView;

public interface TemplateFactory {
	

	public ModelAndView getTemplate(String templateName,String pageName);

}
