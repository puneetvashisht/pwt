package com.sprint2.personalworkout.controller;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.sprint2.personalworkout.entity.User;
import com.sprint2.personalworkout.exception.UserAlreadyExistsException;
import com.sprint2.personalworkout.exception.UserNotFoundException;
import com.sprint2.personalworkout.exception.ValidationException;
import com.sprint2.personalworkout.repository.UserRepository;
import com.sprint2.personalworkout.services.implementation.UserServiceImpl;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/api")
public class UserController {
										
	@Autowired(required = true)
	UserServiceImpl userService;
	@Autowired
	UserRepository userRepository;	
	
	
	@GetMapping
	public String home()
	{
		return "welocme "+new Date();
	}
	
	@PostMapping("/users/add")
	@ApiOperation(value = "Adding the User", notes = "Enter all values to add the user", response = User.class)
	public ResponseEntity<String> addUser(@RequestBody User user) throws ValidationException, UserAlreadyExistsException {		
		User users = userService.addUser(user);
		if (users != null) {
			return new ResponseEntity<>("successfully registered user!!", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<String>("User Not Added", HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/users/login") 
	@ApiOperation(value = "User Login", notes = "Enter your credentials", response = User.class)
	public ResponseEntity<String> login(@ApiParam(value = "Enter your Email Id", required = true)@RequestParam("email") String email,@ApiParam(value = "Enter your Password", required = true)@RequestParam("password") String password) {
		User user = userService.login(email, password);
		if (user!=null) {		    
		     return new ResponseEntity<String>("Successfully Logged in", HttpStatus.OK);
		}
		else {
			 return new ResponseEntity<String>("Use correct email or password",HttpStatus.NOT_FOUND);		
		} 			
	} 
	
	@GetMapping("/users/{id}")
	@ApiOperation(value = "Getting the User By Id", notes = "Enter your UserId", response = User.class)
	public ResponseEntity<User> getUserById(@ApiParam(value = "Enter your Id", required = true)@PathVariable("id") int id) throws UserNotFoundException {
		ResponseEntity<User> response ;
		System.out.println("Recieved id on path: " + id);
		User user = userService.findUserById(id);
		if (user!=null) {			
			response = new ResponseEntity<>(user, HttpStatus.OK);
		} else {
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return response;
	}

	@GetMapping("/users/email")
	@ApiOperation(value = "Getting the User By Email", notes = "Enter your Email", response = User.class)
	public ResponseEntity<User> getUserByEmail(@ApiParam(value = "Enter your Email", required = true)@RequestParam("email") String email) throws UserNotFoundException {
		User existingUser = userService.findByEmail(email);
		if (existingUser == null) {
			
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<User>(existingUser, HttpStatus.OK);
	}

	@GetMapping("/users/viewall")
	@ApiOperation(value = "View all User", notes = "", response = User.class)
	public ResponseEntity<List<User>> getAllUsers() {		
	List<User> users = userService.findAll();
	if(!users.isEmpty()){
		return new ResponseEntity<>(users,HttpStatus.OK);
	}
	else {
		return new ResponseEntity<>( HttpStatus.NOT_FOUND);
	}
}	

	@DeleteMapping("/users/delete/{id}")
	@ApiOperation(value = "Deleting a User By Id", notes = "Enter your UserId", response = User.class)
	public ResponseEntity<String> deleteUser(@ApiParam(value = "Enter your userId", required = true)@PathVariable("id") int id) throws UserNotFoundException {
	
		userService.deleteUser(id);
        return new ResponseEntity<>("Successfully Deleted", HttpStatus.OK);  
	}

	@PutMapping("/users/update")
	@ApiOperation(value = "Editing a User", notes = "Enter your all values including edited values", response = User.class)
	public ResponseEntity<User> updateUser(@ApiParam(value = "Enter your Details", required = true)@RequestBody User userObj) throws UserNotFoundException {		
		User user=userService.updateUser(userObj);
		return new ResponseEntity<User>(user, HttpStatus.CREATED); 
//		if (user!=null) {			
//			return new ResponseEntity<String>("User updated", HttpStatus.CREATED);
//		} else
//			return new ResponseEntity<>("User Not Found", HttpStatus.NOT_FOUND);
//	}
	}

}
