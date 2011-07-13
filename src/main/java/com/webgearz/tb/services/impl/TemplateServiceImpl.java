package com.webgearz.tb.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webgearz.tb.daos.DivContentDao;
import com.webgearz.tb.daos.TemplateDao;
import com.webgearz.tb.domain.models.Template;
import com.webgearz.tb.exceptions.WebgearzException;

import com.webgearz.tb.services.TemplateService;

@Service("templateService")
public class TemplateServiceImpl implements TemplateService{

	private TemplateDao templateDao;
	private DivContentDao divContentDao;
	@Override
	public List<String> getDivsOfTemplate(final String templateId) {
		Template template = templateDao.findById(templateId);
		if(template==null)
			throw new WebgearzException("Unknown template");
		return template.getDivIds();
	}




	@Override
	public List<Template> getAll() {
		return templateDao.getAll();
	}

	@Override
	public Template createTemplate(Template template) {
		return templateDao.save(template);
		
	}


	@Override
	public Template findTemplate(String templateId) {
		return templateDao.findById(templateId);
	}




	public TemplateDao getTemplateDao() {
		return templateDao;
	}



	@Autowired
	public void setTemplateDao(TemplateDao templateDao) {
		this.templateDao = templateDao;
	}




	public DivContentDao getDivContentDao() {
		return divContentDao;
	}



	@Autowired
	public void setDivContentDao(DivContentDao divContentDao) {
		this.divContentDao = divContentDao;
	}

	

}
