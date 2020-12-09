package com.workoutTracker;
import java.util.List;
import java.util.regex.Pattern;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class UserRepository {
	
	private EntityManager em;
	
	public UserRepository()
	{
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
	    em = emf.createEntityManager();
	
	}
	
	public User_user addUser(User_user user) throws ValidationException
	{
		
		String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
		String str = user.getPassword();
		String str1=user.getemail();
		String regex1 = "^(.+)@(.+)$";
		
		if (str1.matches(regex1)) {
			if(str.matches(regex))
		   {
				em.getTransaction().begin();
				em.persist(user);
				em.getTransaction().commit();}
				else {
					throw new ValidationException("use correct password");
				     }
		   }
		else 
		{
			throw new ValidationException("use correct Email");
		}
		
		return user;
		 
	}
	public void editUser(User_user user)
	{   
		
		em.getTransaction().begin();
	    user.setemail("XWX@123");
		em.merge(user);  
		em.getTransaction().commit();
		System.out.println(user);
		
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
