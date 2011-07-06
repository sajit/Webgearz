package com.webgearz.tb.domain.models;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.annotation.Id;
import org.springframework.data.document.mongodb.mapping.Document;

@Document
public class UserDomain {
	
	public static final String COLLECTION_NAME = "userDomain";
	public static final String ID = "_id";
	private String domainName;
	private String templateId;
	@Id
	private String id;
	
	private static final Log log = LogFactory.getLog(UserDomain.class);
	public String getDomainName() {
		return domainName;
	}
	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}
	public String getTemplateId() {
		return templateId;
	}
	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}
	

	
	public UserDomain(String domainName,String templateId){
		this.domainName = domainName;
		this.templateId = templateId;
	}
	
	@Override
	public boolean equals(Object o){
		
		if(!(o instanceof UserDomain))
			return false;
		
		UserDomain thatDomain = (UserDomain)o;
		log.debug("This user " + this+ ", that" + thatDomain);
		return (thatDomain.domainName.equals(domainName));
	}
	
	@Override
	public int hashCode(){
		return this.domainName.hashCode();
	}
	
	@Override
	public String toString(){
		return "{domainName="+this.domainName+",templateId="+this.templateId+"}";
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getId() {
		return id;
	}
	

}
