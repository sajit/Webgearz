package com.webgearz.tb.daos;

import java.util.List;

import com.webgearz.tb.domain.models.Template;

public interface TemplateDao extends GenericDao<Template>{
	
	public List<Template> getAll();

}
