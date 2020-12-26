package com.sprint2.personalworkout.repository;

import com.sprint2.personalworkout.entity.*;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sprint2.personalworkout.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	public User findByEmail(String email);

	@Query("select u from User u where u.email=?1")
	public String getEmail(User user);

	@Query("select u from User u where u.password=?1")
	public String getPassword(User user);

	public User findByEmailAndPassword(String email, String password);

	@Query("select r from Role r where r.roleName=?1")
	public Role findRole(String roleName);

	@Query("select u from User u where u.id=?1")
	public Optional<User> findUsers(int id);

	public User save(Optional<User> updatedUser);

}
