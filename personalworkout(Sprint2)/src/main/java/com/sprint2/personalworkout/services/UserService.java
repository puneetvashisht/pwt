package com.sprint2.personalworkout.services;
import java.util.List;
import com.sprint2.personalworkout.entity.User;
import com.sprint2.personalworkout.exception.UserAlreadyExistsException;
import com.sprint2.personalworkout.exception.UserNotFoundException;
import com.sprint2.personalworkout.exception.ValidationException;

public interface UserService {

	public User addUser(User user) throws UserAlreadyExistsException, ValidationException;

	public User login(String email, String password);
	
	public User findUserById(int id) throws UserNotFoundException;
	
	public User findByEmail(String email)throws UserNotFoundException;
	
	public List<User> findAll();
	
	public User updateUser(User user)throws UserNotFoundException ;

	public void deleteUser(int id) throws UserNotFoundException;
}
