package com.webgearz.tb.daos.impl;

import java.util.List;

import org.springframework.data.document.mongodb.query.Query;
import org.springframework.stereotype.Repository;

import com.webgearz.tb.daos.UserDao;
import com.webgearz.tb.domain.models.User;

@Repository("userDao")
public class UserDaoImpl extends AbstractMongoDao<User,String> implements UserDao{

	@Override
	public List<User> getAll() {
		return getMongoTemplate().find(User.class.getSimpleName(),new Query(),User.class);
	}

}
