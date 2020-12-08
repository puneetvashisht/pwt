package com.workoutTracker.Test;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.omg.CORBA.PUBLIC_MEMBER;

import com.workoutTracker.Entity.Role;
import com.workoutTracker.Entity.User;
import com.workoutTracker.Exception.ValidationException;
import com.workoutTracker.Repository.UserRepository;
import com.workoutTracker.Service.UserService;

public class UserTest {
	private EntityManager em;
	UserRepository obj1 = new UserRepository();
	UserService obj = new UserService();

	 //@Test
	public void addUser() throws ValidationException {
		Role role = new Role("admin");
		Role role1 = new Role("user");
		Role role2 = new Role("trainer");
		User user = new User("murali@123.com", "Murali@123", 180, 76, role);
		User user1 = new User("saiJoshe@123.com", "saiJoshe@123", 190, 75, role1);
		User user2 = new User("Vinitha@123.com", "Vinitha@123", 165, 60, role2);
		User user3 = new User("Ranjith@123.com", "Ranjith@123", 185, 78, role2);
		User user4 = new User("Aishu@123.com", "Aishuu@123456", 176, 65, role2);
		User user5 = new User("chandiPriya@123", "chandiPriya@123", 175, 66, role);

//		obj.add(user);
//		obj.add(user1);
//		obj.add(user2);
//		obj.add(user3);
		obj.add(user5);

	}

	//@Test
	public void loginTest() {
		obj1.loginTest("saiJoshe@123.com", "saiJoshe@123");

	}

	//@Test
	public void editUser() {
		obj.editUser(3, "jackyChan@123.com");
	}

	//@Test
	public void deleteUser() {
		obj.deleteUser(3);

	}

	// @Test
	public void findUserById() {

		obj1.findUser("Aishu@123.com");
	}

}
