package com.webgearz.tb.daos.impl;

import java.lang.reflect.ParameterizedType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.document.mongodb.MongoTemplate;
import org.springframework.data.document.mongodb.query.Criteria;
import org.springframework.data.document.mongodb.query.Query;
import org.springframework.stereotype.Repository;

import com.webgearz.tb.daos.GenericDao;
import com.webgearz.tb.domain.models.AbstractModel;

@Repository("abstractMongoDao")
public abstract class AbstractMongoDao<N extends AbstractModel,E> 
    implements GenericDao<N,E> {
	
	
	protected MongoTemplate mongoTemplate;
	 protected Class<N> persistentClass;
	
	public Class<N> getPersistentClass() {
		return persistentClass;
	}

	@Override
	public N save(N model){
		System.out.println("Inersting into collection " + persistentClass.getSimpleName());
		mongoTemplate.save(model.getCOLLECTION_NAME(),model);
		return model;
		
	}
	
	@Override
	public N findById(E id){
		Query query = new Query(Criteria.whereId().is(id));
		System.out.println("Retrieving into collection "+ persistentClass.getSimpleName());
		
		return (N)mongoTemplate.findOne(persistentClass.getSimpleName(), query, persistentClass);
	}
	
	
	@SuppressWarnings("unchecked")
	public AbstractMongoDao(){
		this.persistentClass =  (Class<N>) ((ParameterizedType) this.getClass()
	            .getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	public MongoTemplate getMongoTemplate() {
		return mongoTemplate;
	}
	@Autowired
	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

}
