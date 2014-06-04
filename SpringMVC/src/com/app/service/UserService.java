package com.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.UserDao;
import com.app.dao.UserTemplateDao;

@Service
//@Transactional 
//在配置文件中已做了事务切面配置，如下。如果没有配置则需要通过@Transactional来标记,数据提交必须要有事务控制
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
