package com.workoutTracker;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.workoutTracker.entities.Category;
import com.workoutTracker.entities.Workout;
import com.workoutTracker.repos.WorkoutRepository;

public class TestWorkout {
	private EntityManager em;
	WorkoutRepository obj =new WorkoutRepository();
	Category category=new Category("strength");
	Category c1=new Category("flexibility");
	Category c2=new Category("endurance");
	
Workout workout=new Workout("Swimming","this comes under endurance category",50,c2);

Workout wc2=new Workout("Lifting weights","this comes under strength category",56,category);
Workout wc3=new Workout("calf stretch","this comes under flexibility category",60,c1);
Workout wc4=new Workout("Using resistance band","this comes under strength category",150,category);

	
	//@Test
	public void addWorkout()
	{
		
		obj.addWorkout(workout);
		obj.addWorkout(wc2);
		obj.addWorkout(wc3);
		obj.addWorkout(wc4);

	}
//@Test
	public void editWorkout()
	{
		obj.editWorkout(workout);
	}
	@Test
	public void deleteWorkout()
	{
		obj.deleteWorkout();
		
	}
	//@Test
	public void FindWorkoutByTitle() {		
			obj.FindWorkoutByTitle("swimming");
	}
	}