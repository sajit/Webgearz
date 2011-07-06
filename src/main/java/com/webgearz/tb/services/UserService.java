package com.webgearz.tb.services;

import java.util.List;

import com.webgearz.tb.domain.models.User;
import com.webgearz.tb.domain.models.UserDomain;

public interface UserService {
	
	public User store(User user);
	public List<User> getAll();
	
	public User findUser(String userId);
	public boolean addDomains(User storedUser, UserDomain userdomain);
	
	public User authenticate(String email,String password);
	

}
