package com.webgearz.tb.domain.models;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.document.mongodb.mapping.Document;

@Document
public class Template {

	
	@Id
	private String id;
	private String templateName;
	private List<String> divIds = new ArrayList<String>();
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTemplateName() {
		return templateName;
	}
	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}
	public List<String> getDivIds() {
		return divIds;
	}
	public void setDivIds(List<String> divIds) {
		this.divIds = divIds;
	}
}
