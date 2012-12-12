package com.webgearz.tb.daos.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;

import com.webgearz.tb.daos.GenericDao;
import com.webgearz.tb.domain.models.AbstractModel;


public abstract class AbstractMongoDao<N extends AbstractModel> 
    implements GenericDao<N> {
	
	
	//protected MongoTemplate mongoTemplate;
	@Autowired
	@Qualifier("mongoTemplate")
	protected MongoOperations mongoOperation;
	 public MongoOperations getMongoOperation() {
		return mongoOperation;
	}

	public void setMongoOperation(MongoOperations mongoOperation) {
		this.mongoOperation = mongoOperation;
	}


	protected Class<N> persistentClass;
	
	public Class<N> getPersistentClass() {
		return persistentClass;
	}

	@Override
	public N save(N model){
		System.out.println("Inersting into collection " + persistentClass.getSimpleName());

		mongoOperation.insert(model,persistentClass.getSimpleName());
		return model;
		
	}
	
	@Override
	public N findById(String id){
		//Query query = new Query(Criteria.where("_id").is(id));
		System.out.println("Retrieving from collection "+ persistentClass.getSimpleName() + " for id " + id);
		return (N)mongoOperation.findById(id, persistentClass,persistentClass.getSimpleName());
		
	}
	
	@Override
	public List<N> getAll()
	{
		return (List<N>)this.mongoOperation.findAll(persistentClass,persistentClass.getSimpleName());
	}	
	@SuppressWarnings("unchecked")
	public AbstractMongoDao(){
		this.persistentClass =  (Class<N>) ((ParameterizedType) this.getClass()
	            .getGenericSuperclass()).getActualTypeArguments()[0];
		 //ApplicationContext ctx = new ClassPathXmlApplicationContext("/WEB-INF/spring/appServlet/mongo-config.xml");
		//this.mongoOperation = (MongoOperations)ctx.getBean("mongoTemplate"); 
		
	}
	
//	public MongoTemplate getMongoTemplate() {
//		return mongoTemplate;
//	}
//	@Autowired
//	public void setMongoTemplate(MongoTemplate mongoTemplate) {
//		this.mongoTemplate = mongoTemplate;
//	}

}
