package com.workoutTracker.Repository;

import java.util.List;
import java.util.regex.Pattern;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.workoutTracker.Entity.Role;
import com.workoutTracker.Entity.User;
import com.workoutTracker.Exception.ValidationException;

public class UserRepository {

	private EntityManager em;

	public UserRepository() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
		em = emf.createEntityManager();

	}

	public void addUser(User user) throws ValidationException {

		em.getTransaction().begin();
		TypedQuery<Role> query = em.createQuery("select r from Role r where r.role_name=:rolename", Role.class);
		query.setParameter("rolename", user.getRole().getRole_name());

		Role role =user.getRole();
		Role existingRole = query.getSingleResult();

		if (existingRole.getRole_name().equals(role.getRole_name())) {
			user.setRole(existingRole);
		}
		em.persist(user);
		em.getTransaction().commit();

	}

	public User getUserById(int id) {
		User use = em.find(User.class, id);
		return use;
	}

	public boolean loginTest(String email, String password) {

		TypedQuery<User> query = em.createQuery("select u from User u where u.email=:emailname ", User.class);

		query.setParameter("emailname", email);
		User existinguser = query.getSingleResult();

		if (existinguser.getemail().equals(email) && existinguser.getPassword().equals(password)) {
			System.out.println("sucessful login");
			return true;
		}

		else {
			System.out.println("Use correct email and password");
			return false;
		}

	}

	public void editUser(User foundUser) {

		em.getTransaction().begin();
		em.merge(foundUser);
		em.getTransaction().commit();

	}

	public void deleteUser(User users) {

		em.getTransaction().begin();
		System.out.println(users);
		em.remove(users);
		em.getTransaction().commit();

	}

	public User findUser(String email) {
		TypedQuery<User> query = em.createQuery("SELECT u FROM User_user u where u.email=:nameparam ", User.class);

		query.setParameter("nameparam", email);
		User users = query.getSingleResult();
		System.out.println(users);
		return users;

	}

}
