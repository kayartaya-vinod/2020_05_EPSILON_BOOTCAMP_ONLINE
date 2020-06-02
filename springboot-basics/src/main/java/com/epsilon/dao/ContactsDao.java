package com.epsilon.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.epsilon.entity.Contact;

@Repository
public interface ContactsDao extends CrudRepository<Contact, Integer> {
}
