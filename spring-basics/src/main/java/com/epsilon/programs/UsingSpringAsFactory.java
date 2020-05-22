package com.epsilon.programs;

import org.springframework.context.support.ClassPathXmlApplicationContext;

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
		ClassPathXmlApplicationContext ctx;
		
		// ctx now refers to an instance of a spring container which loads beans from specified xml file
		ctx = new ClassPathXmlApplicationContext("application-context.xml");
		
		ContactsDao dao = (ContactsDao) ctx.getBean("jdbc-dao");
		long cc = dao.count();
		System.out.println("There are " + cc + " contacts");
		
		ctx.close(); // important - since spring container would have used some native code
	}

}
