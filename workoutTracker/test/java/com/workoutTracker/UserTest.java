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
	

	User_user user = new User_user("murali@123.com","Murali@123",21,23,role);
	User_user user1 = new User_user("jacky@123","Channn@123",44,11,role1);
	User_user user2 = new User_user("Bruce@123","Leeeeeee@123",77,43,role2);
	User_user user3 = new User_user("jack@123","Maaaaaa@123",21,23,role);

	
	//@Test
	public void addUser()throws ValidationException
	{
		
		obj.addUser(user);
		obj.addUser(user1);
		obj.addUser(user2);
		obj.addUser(user3);
		
	}
	//@Test
	public void editUser()
	{
		obj.editUser(user1);
	}
	@Test
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
	
}
