package com.webgearz.tb.daos;

import java.util.List;

import com.webgearz.tb.domain.models.AbstractModel;


public interface GenericDao<N extends AbstractModel> {
	
	public N save(N model);
	public N findById(String id);
	
	public List<N> getAll();

}
