package com.epsilon.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.epsilon.dao.ContactsDao;
import com.epsilon.dao.CsvContactsDao;
import com.epsilon.dao.JdbcContactsDao;

// make these changes in the program:
// AnnotationConfigApplicationContext ctx;
// ctx = new AnnotationConfigApplicationContext(AppConfig1.class);

@Configuration
public class AppConfig1 {

	public AppConfig1() {
		System.out.println("AppConfig1.constructor called");
	}

	// a bean here is an object returned via a function.
	// spring invokes these functions to collect the beans and keep
	// them in the container.
	// A bean function must be annotated with @Bean()
	@Bean(name = { "csvDao", "csv-dao" })
	@Scope("singleton")
	public ContactsDao csvDao() {
		System.out.println("AppConfig1.csvDao() called");
		return new CsvContactsDao();
	}

	@Bean({"jdbc-dao"})
	// @Scope("prototype")
	public ContactsDao jdbcDao() {
		System.out.println("'this' is an instanceof " + this.getClass().getName());
		for(int i=0; i<5; i++) {
			System.out.println("value of i in the loop is " + i);
			this.csvDao(); // this is not an actual call to a function, but accessing the cached bean object (singleton)
		}
		
		System.out.println("AppConfig1.jdbcDao() called");
		String password = "secret";
		String driverClassName = "org.h2.Driver";
		String url = "jdbc:h2:tcp://localhost/~/web-training";
		String username = "vinod";
		return new JdbcContactsDao(driverClassName, url, username, password);
	}
}
