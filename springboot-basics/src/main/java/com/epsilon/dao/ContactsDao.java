package com.epsilon.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.epsilon.entity.Contact;

@Repository
public interface ContactsDao extends PagingAndSortingRepository<Contact, Integer> {
	// functions by convention
	public Iterable<Contact> findByCity(String city);
	public Iterable<Contact> findByGender(String gender);
	
	@Query("select c from Contact c where city=?1 and gender=?2")
	public Iterable<Contact> getContactsBy(String city, String gender);
	
	public Contact findByEmail(String email);
	public Contact findByPhone(String phone);
}
