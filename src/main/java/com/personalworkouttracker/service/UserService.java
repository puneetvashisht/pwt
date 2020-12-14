package com.personalworkouttracker.service;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import com.personalworkouttracker.entity.User;
import com.personalworkouttracker.exception.ValidationException;
import com.personalworkouttracker.repository.UserRepository;

public class UserService {

	UserRepository userRepository = new UserRepository();
	private EntityManager entityManager;

	public UserService() {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("test");
		entityManager = entityManagerFactory.createEntityManager();
	}

   /*
	* The below method is used to Validate and Add User details
	*/
	
	public void add(User user) throws ValidationException {
		String passwordPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
		String password = user.getPassword();
		String email = user.getEmail();
		String emailPattern = "^(.+)@(.+)$";
		if (email.matches(emailPattern)) 
		{
			if (password.matches(passwordPattern))
			{
				userRepository.addUser(user);
			} 
			else {
				    throw new ValidationException("use correct password");
			     }
		} 
		else {
			     throw new ValidationException("use correct Email");
		     }
	}

	/*
	* The below method is used to Edit User details
	*/
	
	public User editUser(int id, String email) {
		User foundUser = entityManager.find(User.class, id);
		foundUser.setEmail(email);
		userRepository.editUser(foundUser);
		return foundUser;
	}
}
