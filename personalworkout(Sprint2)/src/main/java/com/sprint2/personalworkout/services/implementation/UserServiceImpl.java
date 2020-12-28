package com.sprint2.personalworkout.services.implementation;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import com.sprint2.personalworkout.repository.*;
import com.sprint2.personalworkout.services.UserService;
import com.sprint2.personalworkout.entity.Role;
import com.sprint2.personalworkout.entity.User;
import com.sprint2.personalworkout.exception.UserAlreadyExistsException;
import com.sprint2.personalworkout.exception.UserNotFoundException;
import com.sprint2.personalworkout.exception.ValidationException;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	public UserRepository getUserRepository() {
		return userRepository;
	}

	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	private static Logger logger = LogManager.getLogger(UserServiceImpl.class);

	@Override
	public User addUser(User user) throws UserAlreadyExistsException, ValidationException {
		final String passwordPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
		String password = user.getPassword();
		String email = user.getEmail();
		final String emailPattern = "^(.+)@(.+)$";

		if (email.matches(emailPattern)) {
			if (password.matches(passwordPattern)) {
				User existingUser = userRepository.findByEmail(user.getEmail());
				if (existingUser != null) {
					logger.warn("User already Exists");
					throw new UserAlreadyExistsException("User Already Exists");
				}
				Role role = user.getRole();
				Role existingRole = userRepository.findRole(user.getRole().getRoleName());
				if (existingRole.getRoleName().equals(role.getRoleName())) {
					user.setrole(existingRole);
					userRepository.save(user);
				}
			} else {
				logger.warn("use correct password");
				throw new ValidationException("use correct password");
			}
		} else {
			logger.warn("use correct Email");
			throw new ValidationException("use correct Email");
		}
		return user;
	}

	@Override
	@Transactional
	public User login(@RequestBody User user) throws ValidationException {
		String email = user.getEmail();
		String password = user.getPassword();
		User user2 = userRepository.findByEmailAndPassword(email, password);
		if (user2 != null)
			return user2;
		else {
			logger.warn("User Not Found, User correct mail id and password");
			throw new ValidationException("Use correct mail Id and password");
		}
	}

	@Override
	public Optional<User> findUserById(int id) throws UserNotFoundException {
		Optional<User> existingUser = userRepository.findById(id);
		if (!existingUser.isPresent()) {
			logger.warn("User Not Found");
			throw new UserNotFoundException("User Not Found");
		} else {
			return existingUser;
		}
	}

	@Override
	public User findByEmail(String email) throws UserNotFoundException {
		User existingUser = userRepository.findByEmail(email);
		if (existingUser != null) {
			return existingUser;
		} else {
			logger.warn("User Not Found");
			throw new UserNotFoundException("User Not Found");
		}
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public void deleteUser(int userId) throws UserNotFoundException {
		Optional<User> existingUser = userRepository.findById(userId);
		if (!existingUser.isPresent()) {
			logger.warn("User does not exists!!");
			throw new UserNotFoundException("User does not exists!!");
		} else {
			userRepository.deleteById(userId);
		}
	}

	@Override
	@Transactional
	public User updateUser(@RequestBody User user) throws UserNotFoundException {
		User updatedUser = userRepository.findByEmail(user.getEmail());
		if (user.getWeight() != 0) {
			updatedUser.setWeight(user.getWeight());
		}
		if (user.getHeight() != 0) {
			updatedUser.setHeight(user.getHeight());
		}
		if (user.getPassword() != null) {
			updatedUser.setPassword(user.getPassword());
		}
		return updatedUser;
	}

}
