package com.epsilon.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.epsilon.entity.Contact;

@Repository("dao") // allows this class to be instantiated by Spring during component-scan
public class HibernateTemplateContactsDao implements ContactsDao {

	// dependency on HibernateTemplate
	@Autowired
	HibernateTemplate ht;

	public HibernateTemplateContactsDao() {
	}

	@Override
	public void addContact(Contact contact) {
		ht.persist(contact);
	}

	@Override
	public Contact getById(Integer id) {
		return ht.get(Contact.class, id);
	}

	@Override
	public void updateContact(Contact contact) {
		ht.merge(contact);
	}

	@Override
	public void deleteById(Integer id) {
		Contact c = this.getById(id);
		if (c == null)
			throw new RuntimeException("Invalid id");
		ht.delete(c);
	}

	@Override
	public List<Contact> getAll() {
		return (List<Contact>) ht.find("from Contact"); // select c from Contact c
	}

	@Override
	public List<Contact> getByGender(String gender) {
		return (List<Contact>) ht.find("from Contact where gender=?0", gender);
	}

	@Override
	public List<Contact> getByCity(String city) {
		DetachedCriteria dc = DetachedCriteria.forClass(Contact.class).add(Restrictions.eq("city", city));
		return (List<Contact>) ht.findByCriteria(dc);
	}

	@Override
	public Contact getByEmail(String email) {
		DetachedCriteria dc = DetachedCriteria.forClass(Contact.class).add(Restrictions.eq("email", email));
		return (Contact) ht.findByCriteria(dc).get(0);
	}

	@Override
	public Contact getByPhone(String phone) {
		DetachedCriteria dc = DetachedCriteria.forClass(Contact.class).add(Restrictions.eq("phone", phone));
		return (Contact) ht.findByCriteria(dc).get(0);
	}

	@Override
	public long count() {
		// HQL --> select count(c) from Contact c
		DetachedCriteria dc = DetachedCriteria.forClass(Contact.class);
		dc.setProjection(Projections.rowCount());
		return new Long(ht.findByCriteria(dc).get(0).toString());
	}

}
