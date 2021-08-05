package com.example.user.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.user.entity.User;
import com.example.user.service.UserService;

/**
 * Controller class for User.
 * @author ajitkumar_kamble
 *
 */
@RestController
public class UserController {

	@Autowired
	public UserService service;

	@PostMapping("/user")
	public User saveUser(@Valid @RequestBody User user) {
		return service.saveUser(user);
	}

	/**
	 * Controller method used to get user based on Id.
	 * @param id
	 * @return User.
	 */
	@GetMapping("/user/{id}")
	public User getUser(@PathVariable int id) {
		return service.getUserById(id);
	}
	
	/**
	 * Controller method used to update user.
	 * @param id
	 * @param user
	 * @return Updated user
	 */

	@PutMapping("/user/{id}")
	public Optional<Object> updateUser(@PathVariable int id, @RequestBody User user) {
		return service.updateUser(id, user);
	}
	
	/**
	 *  Controller method used to get user based on name.
	 * @param name
	 * @return User
	 */

	@GetMapping("/user/name/{name}")
	public User getUserByName(@PathVariable String name) {
		return service.getUserByName(name);
	}
	
	/**
	 * Controller method used to delete user.
	 * @param id
	 */

	@DeleteMapping("/user/{id}")
	public void deleteUser(@PathVariable int id) {
		service.deleteUserById(id);
	}

}
