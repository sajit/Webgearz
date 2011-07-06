package com.webgearz.tb.daos.impl;

import org.springframework.data.document.mongodb.query.Criteria;
import org.springframework.data.document.mongodb.query.Query;
import org.springframework.stereotype.Repository;

import com.webgearz.tb.daos.DivContentDao;
import com.webgearz.tb.domain.models.DivContent;

@Repository("divContentDao")
public class DivContentDaoImpl extends AbstractMongoDao<DivContent,String> implements DivContentDao{

	@Override
	public DivContent findDivContent(String domainId, String divId) {
		Query query = new Query(Criteria.where(DivContent.DIV_ID).is(divId).and(DivContent.DOMAIN_ID).is(domainId));
		return mongoTemplate.findOne(persistentClass.getSimpleName(), query, persistentClass);

	}

}
