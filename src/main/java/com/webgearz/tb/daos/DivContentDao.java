package com.webgearz.tb.daos;

import com.webgearz.tb.domain.models.DivContent;

public interface DivContentDao extends GenericDao<DivContent>{
	
	public DivContent findDivContent(String domainId,String divId);

}
