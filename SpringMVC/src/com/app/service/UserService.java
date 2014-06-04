package com.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.UserDao;
import com.app.dao.UserTemplateDao;

@Service
//@Transactional 
//�������ļ��������������������ã����¡����û����������Ҫͨ��@Transactional�����,�����ύ����Ҫ���������
//<aop:advisor pointcut="execution(* com.app.service..*Service.*(..))" advice-ref="txAdvice"/>
public class UserService {
	
	@Autowired
	private UserDao userDao;
	@Autowired
	private UserTemplateDao userTemplateDao;
    
	public int userNum(){
		userTemplateDao.userNum();
		return userDao.userNum();
	}

}
