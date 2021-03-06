package com.stackroute.hackathon.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody; 
import org.springframework.web.bind.annotation.ResponseBody; 
import com.stackroute.hackathon.domain.User; 
import com.stackroute.hackathon.services.UserService;

//The main controller for the whole application
@Controller    
@RequestMapping(path="/v1.0/userservice/user") 
public class UserController {
	@Autowired
	private UserService userService;
	 
	//<--- Fetcher Methods ---> 
	@GetMapping(path="/{id:[0-9]+}") 
	public @ResponseBody ResponseEntity<?> fetchUserByID (@PathVariable("id") int id) { 
		try {
			return new ResponseEntity<User>(userService.getUserById(id), HttpStatus.OK);
		}
		catch(Exception e) {  
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	} 
	 
	@GetMapping(path="/{name:[a-zA-Z]+}") 
	public @ResponseBody ResponseEntity<?> getUserByName (@PathVariable("name") String name) {  
		try {
			return new ResponseEntity<User>(userService.getUserByName(name), HttpStatus.OK);
		}
		catch(Exception e) { 
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}  
	
	
	
	//<--- Save Method ---> 
	
	@PostMapping 
	public @ResponseBody ResponseEntity<String> saveNewUser (@RequestBody User user){

		try {
			if(user.getEmailId() == null || user.getFirstname() == null) return new ResponseEntity<String>("Please make sure that both username and email id are entered.", HttpStatus.BAD_REQUEST);
			return new ResponseEntity<String>(userService.saveUser(user), HttpStatus.OK);
		}
		catch(Exception e) { 
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	 
	
	//<--- Delete Methods ---> 
	@DeleteMapping
	public @ResponseBody ResponseEntity<String> deleteUser (@RequestBody User movie){
		try {
			return new ResponseEntity<String>(userService.deleteUser(movie), HttpStatus.OK);
		}
		catch(Exception e) { 
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	} 

	@DeleteMapping(value = "/{id:[0-9]+}")
	public @ResponseBody ResponseEntity<String> deleteUserById (@PathVariable("id") int id){ 
		try {
			return new ResponseEntity<String>(userService.deleteUserById(id), HttpStatus.OK);
		}catch(Exception e) { 
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	 
	
	
	//<--- Get All Methods --->  
	@GetMapping
	public @ResponseBody ResponseEntity<?> getAllUsers() { 
		try {
			return new ResponseEntity<Iterable<User>>(userService.getAllUsers(), HttpStatus.OK);
		}
		catch(Exception e) { 
			return  new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	//<--- Update Methods --->  
		@PutMapping
		public @ResponseBody ResponseEntity<?> updateUser(@RequestBody User user) { 
			try {
				if(user.getEmailId() == null || user.getFirstname() == null) return new ResponseEntity<String>("Please make sure that both username and email id are entered.", HttpStatus.BAD_REQUEST);
				return new ResponseEntity<String>(userService.updateUser(user), HttpStatus.OK);
			}
			catch(Exception e) { 
				return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
			}
		}
}
