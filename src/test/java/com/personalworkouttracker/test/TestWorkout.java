package com.personalworkouttracker.test;

import javax.persistence.EntityManager;

import org.junit.Test;

import com.personalworkouttracker.entity.Category;
import com.personalworkouttracker.entity.Workout;
import com.personalworkouttracker.repository.WorkoutRepository;

import com.personalworkouttracker.exception.ValidationException;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestWorkout {
	private EntityManager em;
	WorkoutRepository obj = new WorkoutRepository();
	Category category = new Category("strength");
	Category c1 = new Category("flexibility");
	Category c2 = new Category("endurance");
	Category c3 = new Category("balance");

@Test
	public void addWorkout() {
		// Workout wc=new Workout("Swimming","this comes under endurance category",150,c2);
		// Workout wc2=new Workout("Stretching","It improves strength",90,category);
		// Workout wc3=new Workout("calf stretch","this comes under flexibility category",57,c1);
		//Workout wc4 = new Workout("yoga","this comes under balance category", 57, c3);
		// obj.addWorkout(wc);
		// obj.addWorkout(wc2);
		// obj.addWorkout(wc3);
		//obj.addWorkout(wc4);
	    Workout wc5 = new Workout("runnning", "this comes under endurance",180, c2);
        obj.addWorkout(wc5);
	}

//@Test
	public void testEditWorkoutByid() {
		obj.updateWorkoutById(7);
     }

//@Test
	public void testEditWorkoutByCbpm() {
		obj.updateWorkoutByCbpm(157);

	}

//@Test
	public void testDeleteWorkoutByTitle() {
		obj.deleteWorkoutByTitle("walk");
	}

//@Test
	public void testDeleteWorkoutById() {
		obj.deleteWorkoutById(173);
	}

//@Test
	public void testFindByTitle() {
		obj.findByTitle("Yogasana");
	}

//@Test
	public void testFindById() {
		obj.findById(7);
	}

}