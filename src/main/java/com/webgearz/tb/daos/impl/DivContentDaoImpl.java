package com.webgearz.tb.daos.impl;


import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.webgearz.tb.daos.DivContentDao;
import com.webgearz.tb.domain.models.DivContent;

@Component("divContentDao")
public class DivContentDaoImpl extends AbstractMongoDao<DivContent> implements DivContentDao{

	@Override
	public DivContent findDivContent(String domainId, String divId) {
		Query query = new Query(Criteria.where(DivContent.DIV_ID).is(divId).and(DivContent.DOMAIN_ID).is(domainId));
		return mongoOperation.findOne(query, persistentClass,persistentClass.getSimpleName());

	}

}
