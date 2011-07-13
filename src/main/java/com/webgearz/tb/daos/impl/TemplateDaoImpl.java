package com.webgearz.tb.daos.impl;

import java.util.List;

import org.springframework.data.document.mongodb.query.Query;
import org.springframework.stereotype.Repository;

import com.webgearz.tb.daos.TemplateDao;
import com.webgearz.tb.domain.models.Template;

@Repository("templateDao")
public class TemplateDaoImpl extends AbstractMongoDao<Template,String> implements TemplateDao{

	@Override
	public List<Template> getAll() {
		return getMongoTemplate().find(Template.class.getSimpleName(),new Query(),Template.class);
	}

}
