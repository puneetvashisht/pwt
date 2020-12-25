package com.personalworkouttracker.repository;

import com.personalworkouttracker.exception.ValidationException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.personalworkouttracker.entity.Category;
import com.personalworkouttracker.entity.Workout;
import javax.persistence.NoResultException;

import javax.persistence.Entity;

public class WorkoutRepository {

	private EntityManager em;

	public WorkoutRepository() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
		em = emf.createEntityManager();

	}

	public void addWorkout(Workout workout) {
		em.getTransaction().begin();
		Category category = workout.getCategory();
		TypedQuery<Category> query = em.createQuery("select c from Category c where c.cname=:cname", Category.class);
		query.setParameter("cname", workout.getCategory().getCname());
		System.out.println(workout.getCategory().getCname());
		Category existingCategory = query.getSingleResult();
		if (existingCategory.getCname().equals(category.getCname())) {
			workout.setCategory(existingCategory);
		}
		em.persist(workout);
		em.getTransaction().commit();
	  }


	public void updateWorkoutById(int id) {
		try {
			em.getTransaction().begin();
			TypedQuery<Workout> query = em.createQuery("SELECT u FROM Workout u where id=:entityId ", Workout.class);
			query.setParameter("entityId", id);
			Workout foundworkout = query.getSingleResult();
			foundworkout.setTitle("Running");
			em.merge(foundworkout);
			em.getTransaction().commit();

		} catch (NoResultException e) {
			System.out.println("Invalid id given by the user");
		}

	}

	public void updateWorkoutByCbpm(int cbpm) {

		try {
			em.getTransaction().begin();
			TypedQuery<Workout> query = em.createQuery("SELECT u FROM Workout u where cbpm=:cbpm", Workout.class);
			query.setParameter("cbpm", cbpm);
			Workout w = query.getSingleResult();
			w.setTitle("Yogasana");
			em.merge(w);
			em.getTransaction().commit();

		} catch (NoResultException e) {
			System.out.println("Invalid cbpm given by user");
		}

	}

	public void deleteWorkoutById(int id) {
		try {
			em.getTransaction().begin();
			TypedQuery<Workout> query = em.createQuery("SELECT u FROM Workout u where id=:entityId ", Workout.class);
			query.setParameter("entityId", id);
			Workout foundworkout = query.getSingleResult();
			em.remove(foundworkout);
			em.getTransaction().commit();

		} catch (NoResultException e) {
			System.out.println("Invalid id given by the user");
		}
	}

	public void deleteWorkoutByTitle(String title) {
		try {
			em.getTransaction().begin();
			TypedQuery<Workout> query = em.createQuery("SELECT u FROM Workout u where title=:title ", Workout.class);
			query.setParameter("title", title);
			Workout w = query.getSingleResult();
			em.remove(w);
			em.getTransaction().commit();
		} catch (NoResultException e) {
			System.out.println("Invalid title given by the user");
		}
	}

	public void findById(int id) {
		try {
			TypedQuery<Workout> query = em.createQuery("SELECT u FROM Workout u where u.id=:entityId ", Workout.class);
			query.setParameter("entityId", id);
			Workout w = query.getSingleResult();
			System.out.println(w);
		} catch (NoResultException e) {
			System.out.println("Invalid Id given by the user");
		}
	}

	public void findByTitle(String title) {
		try {
			TypedQuery<Workout> query = em.createQuery("SELECT u FROM Workout u where title=:nameparam ",
					Workout.class);
			query.setParameter("nameparam", title);
			Workout w = query.getSingleResult();
			System.out.println(w);
		} catch (NoResultException e) {
			System.out.println("Invalid title given by the user");
		}
	}
}