package com.personalworkouttracker.repository;

import javax.persistence.EntityManager;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.personalworkouttracker.entity.*;

import java.text.ParseException;
import java.time.*;
import java.util.Iterator;
import java.util.List;

public class WorkoutActiveRepository {

	private EntityManager em;

	public WorkoutActiveRepository() {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");

		em = emf.createEntityManager();
	}

	public WorkoutTracker assignWorkoutToUser(WorkoutTracker work) {

		em.getTransaction().begin();

		em.persist(work);

		em.getTransaction().commit();
		return work;

	}

	public boolean StartWorkout(int id) {
		WorkoutTracker esc = em.find(WorkoutTracker.class, id);
        if(esc!=null) {
		em.getTransaction().begin();
		LocalTime lt = LocalTime.now();
		String as = lt.toString();
		esc.setStart_datetime(as);

		em.getTransaction().commit();
		System.out.println("Workout Started Successfully");
		return true;
		}
        else {
        	System.out.println("Invalid Id...Id not found in Entity");
		return false;
        }
	}

	public boolean EndWorkout(int id) {
		WorkoutTracker esc = em.find(WorkoutTracker.class, id);
		
		if(esc!=null  && esc.getStart_datetime()!=null) {

		em.getTransaction().begin();
		LocalTime lt = LocalTime.now();
		String as = lt.toString();

		esc.setEnd_datetime(as);

		em.getTransaction().commit();
		System.out.println("Workout Ended Successfully");
		
		return true;
		}
		else {
			System.out.println("Id not found in entity or Please start the workout first");
			return false;
		}

	}

	public void Total_Calories(int calories, int id) throws ParseException {
		WorkoutTracker esc = em.find(WorkoutTracker.class, id);

		em.getTransaction().begin();
		esc.setTotal_calories_burnt(calories);

		em.getTransaction().commit();
	}

	public void displayrecord() {
		TypedQuery<WorkoutTracker> query = em.createQuery("SELECT w FROM WorkoutTracker w", WorkoutTracker.class);
		List<WorkoutTracker> users = query.getResultList();
		Iterator<WorkoutTracker> itr = users.iterator();
		while (itr.hasNext()) {
			System.out.println(itr.next());
			System.out.println(" ");
		}
	}

	
	public void displayByEmail(String email) {
		TypedQuery<WorkoutTracker> query = em.createQuery("SELECT w FROM WorkoutTracker w WHERE w.user_email=:name",
				WorkoutTracker.class);
		query.setParameter("name", email);
	
			List<WorkoutTracker> user = query.getResultList();
			System.out.println(user);
		
		

	}

}