package com.workoutTracker.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.workoutTracker.Entity.Workout;

import javax.persistence.TypedQuery;
import com.workoutTracker.Entity.Category;

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

	public void editWorkout(Workout workout) {

		em.getTransaction().begin();
		workout.setTitle("dancing");
		em.merge(workout);
		em.getTransaction().commit();

	}

	public void deleteWorkout() {
		em.getTransaction().begin();
		Workout foundUser = em.find(Workout.class, 5);
		System.out.println(foundUser);
		em.remove(foundUser);

		em.getTransaction().commit();

	}

	public void FindWorkoutByTitle(String title) {
		TypedQuery<Workout> query = em.createQuery("SELECT w FROM Workout w where w.workout=:title ", Workout.class);
		query.setParameter("title", title);
		Workout wc = query.getSingleResult();
		System.out.println(wc);

	}

	public void FindWorkoutById(int id) {
		TypedQuery<Workout> query = em.createQuery("SELECT DISTINCT w FROM Workout w where w.id=:entityId ",
				Workout.class);
		query.setParameter("entityId", id);
		Workout wc = query.getSingleResult();
		System.out.println(wc);

	}

}