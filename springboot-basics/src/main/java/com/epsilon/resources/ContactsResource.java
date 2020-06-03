package com.epsilon.resources;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.epsilon.dao.ContactsDao;
import com.epsilon.entity.Contact;

import lombok.extern.slf4j.Slf4j;

// @Scope("request")
@Slf4j
@RestController
@RequestMapping("/api/contacts")
public class ContactsResource {

	private static final String JSON = "application/json";

	@Autowired
	ContactsDao dao;

	public ContactsResource() {
		log.info("dao is  {}", dao);
	}

	@PostConstruct
	public void init() {
		log.info("dao is an instanceof: {}", dao.getClass().getName());
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable Integer id) {
		log.debug("ContactService.getById({}) is called", id);
//		try {
//			return ResponseEntity.ok(dao.findById(id).get());
//		} catch (Exception e) {
//			return ResponseEntity.notFound().build();
//		}
		return ResponseEntity.ok(dao.findById(id).get());
	}

	// http://localhost:8080/api/contacts
	// http://localhost:8080/api/contacts?page=3
	// http://localhost:8080/api/contacts?limit=5
	// http://localhost:8080/api/contacts?page=3&limit=5
	@GetMapping
	public Iterable<Contact> getAll(@RequestParam(defaultValue = "0") Integer page,
			@RequestParam(defaultValue = "10") Integer limit) {
		return dao.findAll(PageRequest.of(page, limit)).getContent();
	}

	@GetMapping("/by")
	public ResponseEntity<?> getByCityGender(@RequestParam(defaultValue = "") String city,
			@RequestParam(defaultValue = "") String gender) {
		if (!city.trim().equals("") && !gender.trim().equals("")) {
			return ResponseEntity.ok(dao.getContactsBy(city, gender));
		}

		if (city.trim().equals("") && gender.trim().equals("")) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Either city or gender required as query string parameter");
		}

		if (!city.trim().equals("")) {
			return ResponseEntity.ok(dao.findByCity(city));
		}

		return ResponseEntity.ok(dao.findByGender(gender));
	}

	@PostMapping(consumes = JSON, produces = JSON)
	public Contact addNew(@RequestBody Contact contact) {
		contact.setId(null);
		contact = dao.save(contact);
		return contact;
	}

	@PutMapping(path = "/{id}", consumes = JSON, produces = JSON)
	public Contact updateContact(@PathVariable Integer id, @RequestBody Contact contact) {
		contact.setId(id);
		contact = dao.save(contact);
		return contact;
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Integer id) {
		dao.deleteById(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}


}
