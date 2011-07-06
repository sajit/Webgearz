package com.webgearz.tb.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webgearz.tb.domain.models.Template;
import com.webgearz.tb.exceptions.WebgearzException;
import com.webgearz.tb.mongo.repositories.DivContentRepository;
import com.webgearz.tb.mongo.repositories.TemplateRepository;
import com.webgearz.tb.services.TemplateService;

@Service("templateService")
public class TemplateServiceImpl implements TemplateService{

	private TemplateRepository templateRepository;
	private DivContentRepository divContentRepository;
	@Override
	public List<String> getDivsOfTemplate(final String templateId) {
		Template template = templateRepository.findOne(templateId);
		if(template==null)
			throw new WebgearzException("Unknown template");
		return template.getDivIds();
	}


	@Autowired
	public void setTemplateRepository(TemplateRepository templateRepository) {
		this.templateRepository = templateRepository;
	}

	public TemplateRepository getTemplateRepository() {
		return templateRepository;
	}

	@Autowired
	public void setDivContentRepository(DivContentRepository divContentRepository) {
		this.divContentRepository = divContentRepository;
	}

	public DivContentRepository getDivContentRepository() {
		return divContentRepository;
	}



	@Override
	public List<Template> getAll() {
		return templateRepository.findAll();
	}

	@Override
	public Template createTemplate(Template template) {
		templateRepository.save(template);
		return template;
		
	}


	@Override
	public Template findTemplate(String templateId) {
		return templateRepository.findOne(templateId);
	}

	

}
