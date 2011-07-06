package com.webgearz.tb.daos;

import com.webgearz.tb.domain.models.AbstractModel;


public interface GenericDao<N extends AbstractModel,E> {
	
	public N save(N model);
	public N findById(E id);

}
