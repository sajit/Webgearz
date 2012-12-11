package com.webgearz.tb.daos;

import java.util.List;

import com.webgearz.tb.domain.models.User;

public interface UserDao extends GenericDao<User>{
	
	public List<User> getAll();

}
