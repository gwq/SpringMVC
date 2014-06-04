package com.app.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.app.model.TestModel;

@Repository
public class UserTemplateDao  extends HibernateDaoSupport{
	
	@Autowired
	public void setSessionFacotry(SessionFactory sessionFactory){
		super.setSessionFactory(sessionFactory);
	}
	
	public int userNum(){
		
		TestModel tm = new TestModel();
		//tm.setId(1);
		tm.setName("ds");
		this.getHibernateTemplate().save(tm);
		return 3;
	}

}
