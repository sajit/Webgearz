package com.webgearz.tb.domain.models;

import org.springframework.data.document.mongodb.mapping.Document;

@Document
public class DivContent extends AbstractModel{

	
	public static final String DOMAIN_ID = "domainId";
	public static final String DIV_ID = "divId";
	
	
	
	public String getDivContents() {
		return divContents;
	}
	public void setDivContents(String divContents) {
		this.divContents = divContents;
	}
	public void setDivId(String divId) {
		this.divId = divId;
	}
	public String getDivId() {
		return divId;
	}
	public void setDomainId(String domainId) {
		this.domainId = domainId;
	}
	public String getDomainId() {
		return domainId;
	}
	private String domainId;
	private String divId;
	private String divContents = "Default Text";
}
