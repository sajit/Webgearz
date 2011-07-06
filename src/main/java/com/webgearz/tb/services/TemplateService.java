package com.webgearz.tb.services;

import java.util.List;

import com.webgearz.tb.domain.models.DivContent;
import com.webgearz.tb.domain.models.Template;

public interface TemplateService {
	
	public List<String> getDivsOfTemplate(String templateId);
	
	
	
	
	public List<Template> getAll();
	
	public Template createTemplate(Template template);
	
	public Template findTemplate(String templateId);

}
