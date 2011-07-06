package com.webgearz.tb.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.document.mongodb.MongoTemplate;
import org.springframework.data.document.mongodb.query.Criteria;
import org.springframework.data.document.mongodb.query.Query;
import org.springframework.stereotype.Service;

import com.webgearz.tb.daos.DivContentDao;
import com.webgearz.tb.domain.models.DivContent;
import com.webgearz.tb.services.DivContentService;

@Service("divContentService")
public class DivContentServiceImpl implements DivContentService{

	private DivContentDao divContentDao;
	
	

	@Override
	public String getDivContent(String domainId, String divId) {
		
		
		DivContent divContent = divContentDao.findDivContent(domainId, divId);
		
		return (divContent == null)? "Default text" : divContent.getDivContents();
		
	}
	


	@Override
	public DivContent storeDivContent(DivContent divContent) {
		DivContent dbDivContent = divContentDao.findDivContent(divContent.getDomainId(), divContent.getDivId());
		if(dbDivContent==null){
			dbDivContent = new DivContent();
			dbDivContent.setDomainId(divContent.getDomainId());
			dbDivContent.setDivId(divContent.getDivId());
		}
		dbDivContent.setDivContents(divContent.getDivContents());
		
		divContentDao.save(dbDivContent);

		return dbDivContent;
		
	}
	@Override
	public DivContent getDivContentById(String id){
		return divContentDao.findById(id);
	}
	public DivContentDao getDivContentDao() {
		return divContentDao;
	}

	@Autowired
	public void setDivContentDao(DivContentDao divContentDao) {
		this.divContentDao = divContentDao;
	}

}
