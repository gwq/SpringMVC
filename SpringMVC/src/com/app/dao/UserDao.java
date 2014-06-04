package com.app.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.app.model.TestModel;

@Repository
public class UserDao{
	
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
