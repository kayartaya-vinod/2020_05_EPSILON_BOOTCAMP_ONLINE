package com.epsilon.programs;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.orm.hibernate5.HibernateTemplate;

import com.epsilon.configuration.AppConfig5;
import com.epsilon.entity.Contact;

public class HibernateTemplateDemo {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx;
		ctx = new AnnotationConfigApplicationContext(AppConfig5.class);
		
		HibernateTemplate ht = ctx.getBean(HibernateTemplate.class);
		
		Contact c1 = ht.get(Contact.class, 12);
		System.out.println(c1);
		
		// select * from contacts where gender = 'Male'
		// select c from Contact where gender = ?0
		String hql = "select c from Contact c where gender = ?0";
		String gender = "Female";
		List<Contact> list = (List<Contact>) ht.find(hql, gender);
		System.out.println("There are " + list.size() + " " + gender + " contacts");
		
		gender = "Male";
		// Detached --> from a session object
		DetachedCriteria dc = DetachedCriteria.forClass(Contact.class); // select c from Contact c
		dc.add(Restrictions.eq("gender", gender)); // where gender='Female'
		// dc.add(Restrictions.eq("city", "Bangalore")); // and city='Bangalore'
		
		list = (List<Contact>) ht.findByCriteria(dc); // dc is attached to the session here
		System.out.println("There are " + list.size() + " " + gender + " contacts");
		
		ctx.close();
	}
}
