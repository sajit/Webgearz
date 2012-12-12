package com.webgearz.tb.daos.impl;

import org.springframework.stereotype.Component;

import com.webgearz.tb.daos.TemplateDao;
import com.webgearz.tb.domain.models.Template;

@Component("templateDao")
public class TemplateDaoImpl extends AbstractMongoDao<Template> implements TemplateDao{


}
