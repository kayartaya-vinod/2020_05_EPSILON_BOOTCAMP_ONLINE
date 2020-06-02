package com.epsilon.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.epsilon.dao.ContactsDao;
import com.epsilon.entity.Contact;

@RestController
@RequestMapping("/api/contacts")
public class ContactsResource {
	
	@Autowired
	ContactsDao dao;

	@GetMapping("/{id}")
	public Contact getById(@PathVariable Integer id) {
		return dao.findById(id).get();
	}
	
	
}
