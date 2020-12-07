package com.workoutTracker.entities;

import javax.persistence.EntityManager;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.time.*;
import java.util.Iterator;
import java.util.List;
public class WorkoutActiveRepository {
	
	private EntityManager em;

	@Before
	public void setUp() {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
	

		em = emf.createEntityManager();
	}
	
	
	@Test
	public void assignWorkoutToUser() {
		User_user use = em.find(User_user.class, 3);
		String a = use.getemail();
		int b = use.getId();
	
		WorkoutTracker work = new WorkoutTracker(47,"bicycling",b,a);	
		//em.persist
		em.getTransaction().begin();
		
		
		em.persist(work);
		
		
		em.getTransaction().commit();
		
	}

	//@Test 
	public void StartWorkout() {		
		WorkoutTracker esc = em.find(WorkoutTracker.class, 2);
		
		em.getTransaction().begin();
		 LocalTime lt 
         = LocalTime.now(); 
		 String as = lt.toString();
		esc.setStart_datetime(as);
		
		em.getTransaction().commit();
		
	}
	
	//@Test 
	public void EndWorkout() {		
		WorkoutTracker esc = em.find(WorkoutTracker.class, 3);
		
		em.getTransaction().begin();
		 LocalTime lt 
         = LocalTime.now(); 
		 String as = lt.toString();
		 
		esc.setEnd_datetime(as);
		
	
		em.getTransaction().commit();
		
	}

	//@Test
	public void Total_Calories() throws ParseException {
		WorkoutTracker esc = em.find(WorkoutTracker.class, 3);
		String a = esc.getStart_datetime();
		String b =esc.getEnd_datetime();
		long c = esc.calculateCalories(a,b);
		em.getTransaction().begin();
		
		if(c>120) {
	if(c>3600) {
		esc.setTota_calories_burnt("25");
	}else if(c>2400) {
		esc.setTota_calories_burnt("17.5");
	}
	else if(c>1200) {
		esc.setTota_calories_burnt("12");
	}
	else if(c>600) {
		esc.setTota_calories_burnt("6");
	}
		}
	else {
		esc.setTota_calories_burnt("1");
		
	}
		
		em.getTransaction().commit();
	}
	
	//@Test
	public void displayrecord() {
		TypedQuery<WorkoutTracker> query = em.createQuery("SELECT w FROM WorkoutTracker w",WorkoutTracker.class);
		List<WorkoutTracker> users = query.getResultList();
		Iterator<WorkoutTracker> itr = users.iterator();
        while (itr.hasNext()) {
            System.out.println(itr.next());
            System.out.println(" ");
        }
		
		
		
	}

	
	@After
	public void destroy() {
		em.close();
	}

}