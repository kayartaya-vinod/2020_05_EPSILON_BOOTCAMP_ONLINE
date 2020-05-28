package com.epsilon.programs;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.epsilon.configuration.AppConfig1;
import com.epsilon.dao.ContactsDao;

public class UsingSpringAsFactory {

	public static void main(String[] args) {

		// create a spring container
		// which loads beans from a configuration file (XML or annotation based)
		
		// A spring container is a class that implements an interface called ApplicationContext
		// 1. ClassPathXmlApplicationContext
		// 2. FileSystemXmlApplicationContext
		// 3. AnnotationConfigApplicationContext
		
		// ctx is a variable representing a spring container
		AnnotationConfigApplicationContext ctx;
		
		// ctx now refers to an instance of a spring container which loads beans from specified xml file
		// At this time, all singleton beans will be instantiated
		// ctx = new ClassPathXmlApplicationContext("application-context.xml");
		ctx = new AnnotationConfigApplicationContext(AppConfig1.class);
		
		System.out.println("---------");
		
		// at this time, if the bean is having the scope of "prototype", it will be instantiated!
		ContactsDao dao = ctx.getBean("jdbc-dao", ContactsDao.class);
		long cc = dao.count();
		System.out.println("There are " + cc + " contacts");
		
		// at this time, if the bean is having the scope of "prototype", it will be instantiated!
		ContactsDao dao2 = ctx.getBean("jdbc-dao", ContactsDao.class);
		
		System.out.println("dao==dao2 is "+ (dao==dao2));
		
		ctx.close(); // important - since spring container would have used some native code
	}

}
