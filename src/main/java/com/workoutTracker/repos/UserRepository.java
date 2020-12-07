package com.workoutTracker.repos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.workoutTracker.entities.User_user;
import com.workoutTracker.entities.Workout;


public class UserRepository {
	
	private EntityManager em;
	Workout obj = new Workout();
	
	public UserRepository()
	{
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
	    em = emf.createEntityManager();
	
	}
	public User_user addUser(User_user user)
	{
		
		em.getTransaction().begin();
		em.persist(user);
		em.getTransaction().commit();
		
		return user;
		 
	}
	public User_user editUser(User_user user)
	{   
		
		em.getTransaction().begin();
	    user.setemail("joe@123");
		em.merge(user);  
		em.getTransaction().commit();
		return user;
	}
	public void deleteUser()
	{
		em.getTransaction().begin();
		User_user foundUser = em.find(User_user.class, 3);
		em.remove(foundUser);
		em.getTransaction().commit();
	
	}
	public User_user findUser()
	{
		TypedQuery<User_user> query = em.createQuery("SELECT u FROM User_user u where email=:nameparam ", User_user.class);
		query.setParameter("nameparam", "murali@123");
        User_user users = query.getSingleResult();
		System.out.println(users);
		return users;
		
	}
	
	
	
}
