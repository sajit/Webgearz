package com.webgearz.tb.domain.models;

import org.springframework.data.annotation.Id;

public abstract class AbstractModel{
	
	@Id
	protected String id;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	protected String COLLECTION_NAME; 
	
	
	
	public AbstractModel(){
		COLLECTION_NAME = this.getClass().getSimpleName();
		
	}
	
	
	
	public String getCOLLECTION_NAME() {
		return COLLECTION_NAME;
	}

	
	
	

}
