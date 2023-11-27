package com.codewithshubham.fullstackbackend1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codewithshubham.fullstackbackend1.exception.UserNotFoundException;
import com.codewithshubham.fullstackbackend1.model.User;
import com.codewithshubham.fullstackbackend1.repository.UserRepository;

@RestController
@RequestMapping("/full")
@CrossOrigin
public class Usercontroller {
	
	@Autowired
	private UserRepository userrepository;
	
	@PostMapping("/user1")
	User newUser(@RequestBody User newUser) {
		return userrepository.save(newUser);
	}
	
	@GetMapping("/users")
	List<User>getAllUsers(){
		return userrepository.findAll();
	}
	
	@GetMapping("/user/{id}")
	User getUserById(@PathVariable("id") Long id) {
		return userrepository.findById(id)
				.orElseThrow(()->new UserNotFoundException(id));
		
	}
	@PutMapping("/user/{id}")
	User updateUser(@RequestBody User newUser,@PathVariable Long id) {
		return userrepository.findById(id)
				.map(user ->{
					user.setUsername(newUser.getUsername());
					user.setName(newUser.getName());
					user.setEmail(newUser.getEmail());
					return userrepository.save(user);
				}).orElseThrow(()->new UserNotFoundException(id));
	}
	
	@DeleteMapping("/user/{id}")
	String deleteUser(@PathVariable Long id) {
		if(!userrepository.existsById(id)) {
			throw new UserNotFoundException(id);
		}
		userrepository.deleteById(id);
		return "User with id "+id+" has been deleted";
		
	}

}
