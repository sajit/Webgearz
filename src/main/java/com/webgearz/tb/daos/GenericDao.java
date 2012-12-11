package com.webgearz.tb.daos;

import com.webgearz.tb.domain.models.AbstractModel;


public interface GenericDao<N extends AbstractModel> {
	
	public N save(N model);
	public N findById(String id);

}
