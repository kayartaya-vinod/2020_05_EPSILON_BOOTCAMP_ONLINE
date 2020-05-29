package com.epsilon.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;

import com.epsilon.entity.Contact;

@Transactional(rollbackFor = { DataAccessException.class }, readOnly = true)
public interface ContactsDao {
	
	// CRUD operations
	@Transactional(readOnly = false)
	default void addContact(Contact contact) { throw new RuntimeException("Method not implemented"); }
	default Contact getById(Integer id)  { throw new RuntimeException("Method not implemented"); }
	@Transactional(readOnly = false)
	default void updateContact(Contact contact) { throw new RuntimeException("Method not implemented"); }
	@Transactional(readOnly = false)
	default void deleteById(Integer id) { throw new RuntimeException("Method not implemented"); }
	// Queries
	default List<Contact> getAll() { throw new RuntimeException("Method not implemented"); }
	default List<Contact> getByGender(String gender) { throw new RuntimeException("Method not implemented"); }
	default List<Contact> getByCity(String city) { throw new RuntimeException("Method not implemented"); }
	default Contact getByEmail(String email) { throw new RuntimeException("Method not implemented"); }
	default Contact getByPhone(String phone) { throw new RuntimeException("Method not implemented"); }
	public long count(); // supposed to give the number of contacts from DB 

}
