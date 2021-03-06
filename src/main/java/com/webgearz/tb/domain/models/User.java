package com.webgearz.tb.domain.models;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;


@Document
public class User extends AbstractModel{
	
	public static final String EMAIL = "email";
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	

	private String firstName;
	private String lastName;
	private String email;
	private String password;
	//private List<String> userDomains = new ArrayList<String>();

	
	@Override
	public String toString(){
		return "{name : " + firstName + lastName +",email: "+email+",password"+password+" }";
	}
//	public void setUserDomains(List<String> userDomains) {
//		this.userDomains = userDomains;
//	}
//	public List<String> getUserDomains() {
//		return userDomains;
//	}
	


}
