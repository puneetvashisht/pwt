package com.workoutTracker.repos;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.workoutTracker.entities.Category;
import com.workoutTracker.entities.Workout;

public class WorkoutRepository {
	
		
		private EntityManager em;
		
		
		public WorkoutRepository()
		{
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
		    em = emf.createEntityManager();
		
		}
		public Workout addWorkout(Workout workout)
		{
			
			Category category = workout.getCategory();
//			em.find(Category.class, primaryKey)
			TypedQuery<Category> query = em.createQuery("select c from Category c where c.name=:name", Category.class);
			Category existingCategory = query.getSingleResult();
			if(existingCategory.getCname().equals(category.getCname())) {
				workout.setCategory(existingCategory);
			}
			
			em.getTransaction().begin();
			em.persist(workout);
			em.getTransaction().commit();
			
			return workout;
			 
}
		public Workout editWorkout(Workout workout)
		{   
			
			em.getTransaction().begin();
		    workout.setTitle("dancing");
			em.merge(workout);  
			em.getTransaction().commit();
			return workout;
		}
		public void deleteWorkout()
		{
			em.getTransaction().begin();
			Workout foundUser = em.find(Workout.class, 5);
			System.out.println(foundUser);
			em.remove(foundUser);
			
			em.getTransaction().commit();
		
		}
		public Workout FindWorkoutByTitle(String title)
		{
			TypedQuery<Workout> query = em.createQuery("SELECT w FROM Workout w where w.workout=:title ", Workout.class);
			query.setParameter("title",title);
	       Workout wc = query.getSingleResult();
			System.out.println(wc);
			return wc;
			
		}
		
		

}