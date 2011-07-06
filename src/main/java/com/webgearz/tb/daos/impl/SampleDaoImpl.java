package com.webgearz.tb.daos.impl;

import java.util.List;

import org.springframework.data.document.mongodb.query.Query;

import com.webgearz.tb.daos.SampleDao;
import com.webgearz.tb.domain.models.Sample;


public class SampleDaoImpl extends AbstractMongoDao<Sample,String> implements SampleDao{

	@Override
	public List<Sample> getAll() {
	
		return getMongoTemplate().find(Sample.class.getSimpleName(),new Query(),Sample.class);
	}

	
}
