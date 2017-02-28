package com.jtd.spring4mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jtd.spring4mvc.dao.UserDao;
import com.jtd.spring4mvc.dto.User;

@RestController
public class UserController {

	@Autowired
	private UserDao userDao;

	@GetMapping("/")
	public String sayHello() {
		return "Welcome to Spring 4 MVC Rest Tutorial!!";
	}

	@GetMapping("/users")
	public ResponseEntity getUsers() {
		return new ResponseEntity(userDao.listUsers(), HttpStatus.OK);
	}

	@GetMapping("/users/{id}")
	public ResponseEntity getUser(@PathVariable("id") int id) {
		User customer = userDao.get(id);
		if (customer == null) {
			return new ResponseEntity("No user with id:" + id + " was found.", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(customer, HttpStatus.OK);
	}

	@PostMapping(value = "/users")
	public ResponseEntity createUser(@RequestBody User user) {
		if (userDao.create(user) == null) {
			return new ResponseEntity("User with name:" + user.getName() + " already exists.", HttpStatus.CONFLICT);
		}
		return new ResponseEntity(user, HttpStatus.OK);
	}

	@PutMapping("/users/{id}")
	public ResponseEntity updateCustomer(@PathVariable int id, @RequestBody User user) {
		user = userDao.update(user, id);
		if (null == user) {
			return new ResponseEntity("No user with id:" + id + " was found.", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(user, HttpStatus.OK);
	}

	@DeleteMapping("/users/{id}")
	public ResponseEntity deleteUser(@PathVariable int id) {
		if (!userDao.delete(id)) {
			return new ResponseEntity("No user with id:" + id + " was found.", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(id, HttpStatus.OK);
	}

}
