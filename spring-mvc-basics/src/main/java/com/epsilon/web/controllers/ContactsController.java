package com.epsilon.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.epsilon.dao.ContactsDao;

@Controller
public class ContactsController {

	@Autowired
	ContactsDao dao;
	
	@RequestMapping(path="/all-contacts", method=RequestMethod.GET)
	public String getAllContacts(Model model) { // dependency injection by spring
		model.addAttribute("contacts", dao.getAll()); // model data, accessible to JSPs (views)
		return "show-contacts"; // view resolver resolves this to --> /WEB-INF/views/show-contacts.jsp
	}
	
	@RequestMapping(path="/delete-contact")
	public String deleteContact(@RequestParam("id") Integer id) { // dependencies injected
		dao.deleteById(id);
		return "redirect:./all-contacts"; // client side redirection
	}
}
