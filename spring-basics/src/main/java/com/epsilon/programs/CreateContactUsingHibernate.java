package com.epsilon.programs;

import java.util.Properties;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.epsilon.entity.Contact;

public class CreateContactUsingHibernate {

	public static void main(String[] args) {
		
		Properties props = new Properties();
		props.setProperty("hibernate.connection.driver_class", "org.h2.Driver");
		props.setProperty("hibernate.connection.url", "jdbc:h2:tcp://localhost/~/web-training");
		props.setProperty("hibernate.connection.username", "vinod");
		props.setProperty("hibernate.connection.password", "secret");
		props.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
		
		props.setProperty("hibernate.show_sql", "true");
		props.setProperty("hibernate.format_sql", "true");
		
		Configuration cfg = new Configuration().setProperties(props);
		cfg.addAnnotatedClass(Contact.class);
		
		SessionFactory factory; // a factory for creating a session object, built using a configuration object
		factory = cfg.buildSessionFactory();
		
		Session session; // provides CRUD operations save(), persist(), get(), load(), update(), merge(), delete()
		session = factory.openSession();
		
		Contact c1 = session.get(Contact.class, 14);
		
		
		Contact c2 = new Contact();
		c2.setName("Vinod");
		c2.setCity("Bangalore");
		c2.setEmail("vinod@vinod.co");
		c2.setPhone("9731424784");
		c2.setGender("Male");
		
		Transaction tx = session.beginTransaction();
		try {
			// one or more DML operations
			session.persist(c2);
			tx.commit(); // all DML will be sent to the DB
		}
		catch(Exception ex) {
			tx.rollback();
		}
		
		
		session.close();
		factory.close();
		
		System.out.println(c1);
	}
}
