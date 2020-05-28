package com.epsilon.programs;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.epsilon.configuration.AppConfig5;
import com.epsilon.dao.ContactsDao;
import com.epsilon.entity.Contact;

public class TestingHibernateTemplateContactsDao {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx;
		ctx = new AnnotationConfigApplicationContext(AppConfig5.class);
		
		ContactsDao dao = ctx.getBean("dao", ContactsDao.class);
		System.out.println("dao is an instanceof " + dao.getClass().getName());
		
		long cc = dao.count();
		System.out.println("There are " + cc + " contacts in your phonebook!");
		
		Contact c = dao.getById(51);
		System.out.println("Details of id 51 is: " + c);
		
		String city = "Bangalore";
		List<Contact> list = dao.getByCity(city);
		System.out.println("There are " + list.size() + " contacts in " + city);
		
		list = dao.getByGender("Male");
		System.out.println("There are " + list.size() + " Male contacts.");
		
		c = dao.getByEmail("icaesarc@ow.ly");
		System.out.println("By email = " + c);
		
		c = dao.getByPhone("9844083934");
		System.out.println("By Phone = " + c);
		
		list = dao.getAll();
		System.out.println("There are " + list.size() + " contacts in your phonebook!");
		
		c = dao.getById(51);
		c.setPhone("8026661911");
		c.setCity("Bengaluru");
		dao.updateContact(c);
		
		
		ctx.close();
	}

}
