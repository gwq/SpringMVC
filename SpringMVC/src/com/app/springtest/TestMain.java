package com.app.springtest;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class TestMain {

	public static void main(String[] args) {
		ApplicationContext ac = new FileSystemXmlApplicationContext("WebRoot/WEB-INF/classes/config/spring/applicationContext.xml");
		ReturnTest rt = (ReturnTest)ac.getBean("returnTest");
		System.out.println(rt.intest());
		

	}

}
