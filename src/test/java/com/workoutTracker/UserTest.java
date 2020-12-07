package com.workoutTracker;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UserTest {
	private EntityManager em;
	UserRepository obj =new UserRepository();
	Role role= new Role("admin");
	Role role1=new Role("user");
	Role role2= new Role("trainer");
	

	User_user user = new User_user("murali@123","murali123",21,23,role);
	User_user user1 = new User_user("jacky@123","chan123",44,11,role1);
	User_user user2 = new User_user("Bruce@123","lee123",77,43,role2);
	User_user user3 = new User_user("jack@123","maa123",21,23,role);
//	@Before
//	public void setUp() { 
//		EntityManagerFactory emf = Persistence
//				.createEntityManagerFactory("test");
//
//		em = emf.createEntityManager();
//	}
	
	@Test
	public void addUser()
	{
		
		obj.addUser(user);
		obj.addUser(user1);
		obj.addUser(user2);
		obj.addUser(user3);
		
	}
	//@Test
	public void editUser()
	{
		obj.editUser(user);
	}
	//@Test
	public void deleteUser()
	{
		obj.deleteUser();
		
	}
	//@Test 
	public void findUserById() {		
		//User_user xx = em.find(User_user.class, 1);
		//System.out.println(obj);
			obj.findUser();
	}
	
	//@Test
	
	
//	@After
//	public void destroy() {
//		em.close();
//	}
}
