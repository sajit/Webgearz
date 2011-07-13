package com.webgearz.tb.daos;

import java.util.List;

import com.webgearz.tb.domain.models.Template;

public interface TemplateDao extends GenericDao<Template,String>{
	
	public List<Template> getAll();

}
