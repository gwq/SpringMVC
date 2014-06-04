package com.app.service;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.model.TestModel;

@Service
@Transactional
public class UserTemplateService  extends HibernateDaoSupport{
	
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
