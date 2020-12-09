package com.workoutTracker.Test;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.workoutTracker.Entity.Category;
import com.workoutTracker.Entity.Workout;
import com.workoutTracker.Repository.WorkoutRepository;

public class TestWorkout {
	private EntityManager em;
	WorkoutRepository obj = new WorkoutRepository();
	Category category = new Category("strength");
	Category c1 = new Category("flexibility");
	Category c2 = new Category("endurance");

	// @Test
	public void addWorkout() {
		Workout workout = new Workout("Swimming", "this comes under endurance category", 50, c2);

		Workout wc2 = new Workout("Lifting weights", "this comes under strength category", 56, category);
		Workout wc3 = new Workout("calf stretch", "this comes under flexibility category", 60, c1);
		Workout wc4 = new Workout("Using resistance band", "this comes under strength category", 150, category);
		obj.addWorkout(workout);
		obj.addWorkout(wc2);
		obj.addWorkout(wc3);
		obj.addWorkout(wc4);

	}

//@Test
	public void editWorkout() {
		Workout workout = new Workout("Swimming", "this comes under endurance category", 50, c2);
		obj.editWorkout(workout);
	}

	@Test
	public void deleteWorkout() {
		obj.deleteWorkout();

	}

	// @Test
	public void FindWorkoutByTitle() {
		obj.FindWorkoutByTitle("swimming");
	}
}