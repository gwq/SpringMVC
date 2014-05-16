package com.app.service;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.model.TestModel;

@Service
@Transactional
public class UserService {
	
	@Autowired
	private SessionFactory sessionFactory;

	public int userNum(){
		if (sessionFactory != null){
			System.out.println("SessionFacotory is NOT NULL!!!");
		}
		//Query query = sessionFactory.getCurrentSession().createSQLQuery("INSERT INTO `bo_gefms_testmodel` (`id`,`name`) VALUES (1,'ge')");
		//query.executeUpdate();
		TestModel tm = new TestModel();
		//tm.setId(1);
		tm.setName("ds");
		sessionFactory.getCurrentSession().save(tm);
		return 3;
	}
}
