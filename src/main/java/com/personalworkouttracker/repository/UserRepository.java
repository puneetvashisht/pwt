package com.personalworkouttracker.repository;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import com.personalworkouttracker.entity.Role;
import com.personalworkouttracker.entity.User;
import com.personalworkouttracker.exception.ValidationException;

public class UserRepository {

	private EntityManager entityManager;

	public UserRepository() {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("test");
		entityManager = entityManagerFactory.createEntityManager();

	}

	/*
	* The below method is used to Validate and Add User details
	*/
	
	public void addUser(User user) throws ValidationException {
		entityManager.getTransaction().begin();
		TypedQuery<Role> query = entityManager.createQuery("select r from Role r where r.role_name=:rolename", Role.class);
		query.setParameter("rolename", user.getRole().getRole_name());
		Role role = user.getRole();
		Role existingRole = query.getSingleResult();
		if (existingRole.getRole_name().equals(role.getRole_name())) {
			user.setRole(existingRole);
		}
		entityManager.persist(user);
		entityManager.getTransaction().commit();
	}

   /*
	* The below method is used to Get User details
	*/
	
	public User getUserById(int id) {
		User user = entityManager.find(User.class, id);
		return user;
	}

   /*
	* The below method is used for User Login 
	*/
	
	public boolean loginTest(String email, String password) {
		TypedQuery<User> query = entityManager.createQuery("select u from User u where u.email=:emailname ", User.class);
		query.setParameter("emailname", email);
		User existinguser = query.getSingleResult();
		if (existinguser.getEmail().equals(email) && existinguser.getPassword().equals(password)) {
			System.out.println("sucessful login");
			return true;
		}
		else {
			System.out.println("Use correct email and password");
			return false;
		} 
	}

   /*
	* The below method is used to Edit User details
	*/
	
	public void editUser(User user) {
		entityManager.getTransaction().begin();
		entityManager.merge(user);
		entityManager.getTransaction().commit();
	}
	
   /*
	* The below method is used to Delete User details
	*/

	public User deleteUser(int id, String email) {
		TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u where u.email=:nameparam ", User.class);
		query.setParameter("nameparam", email);
		User user = query.getSingleResult();
		entityManager.getTransaction().begin();
		User foundUser = entityManager.find(User.class, id);
		entityManager.remove(foundUser);
		entityManager.getTransaction().commit();
		user = null;
		return user;
	}

   /*
	* The below method is used to Find the User details
	*/
	public User findUser(String email) {
		TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u where u.email=:name ", User.class);
		query.setParameter("name", email);
		User user = query.getSingleResult();
		return user;
	}

   /*
	* The below method is used collect every User details
	*/
	public List<User> findAllUser() {
		TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u", User.class);
		List<User> userList = query.getResultList();
		return userList;
	}
}
