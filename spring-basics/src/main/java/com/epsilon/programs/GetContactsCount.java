package com.epsilon.programs;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.epsilon.configuration.AppConfig4;
import com.epsilon.dao.ContactsDao;

public class GetContactsCount {

	public static void main(String[] args) {
		
		AnnotationConfigApplicationContext ctx;
		ctx = new AnnotationConfigApplicationContext(AppConfig4.class);
		
		ContactsDao dao = ctx.getBean(ContactsDao.class);
		long cc = dao.count();
		System.out.println("There are " + cc + " contacts");
		
		ctx.close();
	}
}
