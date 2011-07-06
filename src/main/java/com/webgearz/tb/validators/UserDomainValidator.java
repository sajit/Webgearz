package com.webgearz.tb.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.webgearz.tb.domain.models.UserDomain;


public class UserDomainValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return UserDomain.class.equals(clazz);
	}

	@Override
	public void validate(Object arg0, Errors arg1) {
		// TODO Auto-generated method stub
		
	}

	

}
