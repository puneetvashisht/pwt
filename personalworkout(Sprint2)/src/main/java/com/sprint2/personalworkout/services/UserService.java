package com.sprint2.personalworkout.services;

import java.util.List;
import java.util.Optional;
import com.sprint2.personalworkout.entity.User;


public interface UserService {

	 User addUser(User user);

	 User login(User user);

	 Optional<User> findUserById(int id);

	 User findByEmail(String email);

	 List<User> findAll();

	 User updateUser(User user);

	 Optional<User> deleteUser(int id);
}
