package com.webgearz.tb.services;

import com.webgearz.tb.domain.models.DivContent;

public interface DivContentService {
	
	public String getDivContent(String domainId,String divId);
	
	public DivContent storeDivContent(DivContent divContent);

	DivContent getDivContentById(String id);

}
