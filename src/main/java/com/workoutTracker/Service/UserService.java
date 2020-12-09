package com.workoutTracker.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.workoutTracker.Entity.Category;
import com.workoutTracker.Entity.Role;
import com.workoutTracker.Entity.User;
import com.workoutTracker.Exception.ValidationException;
import com.workoutTracker.Repository.UserRepository;

public class UserService {

	UserRepository userRepository = new UserRepository();
	private EntityManager em;

	public UserService() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
		em = emf.createEntityManager();

	}

	public void add(User user) throws ValidationException {
		String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
		String str = user.getPassword();
		String str1 = user.getemail();
		String regex1 = "^(.+)@(.+)$";

		Role role = user.getRole();

		
		if (str1.matches(regex1)) {
			if (str.matches(regex)) {

				userRepository.addUser(user);
			} else {
				throw new ValidationException("use correct password");
			}
		} else {
			throw new ValidationException("use correct Email");
		}

	}
	

	public User editUser(int id, String email) {
		User foundUser = em.find(User.class, id);
		foundUser.setemail(email);
		userRepository.editUser(foundUser);
		return foundUser;

	}

	public void deleteUser(int id) {
		User foundUser = em.find(User.class, id);
//		TypedQuery<User_user> query = em.createQuery("SELECT u FROM User_user u where u.email=:nameparam ", User_user.class);
//		query.setParameter("nameparam", email);
//        User_user users = query.getSingleResult();
		userRepository.deleteUser(foundUser);

	}

}
