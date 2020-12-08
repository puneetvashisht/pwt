package com.workoutTracker;

import static org.junit.Assert.*;

import java.text.ParseException;

import org.junit.Test;
import javax.persistence.EntityManager;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import com.workoutTracker.entities.*;
import com.workoutTracker.repos.WorkoutActiveRepository;
import com.workoutTracker.repos.UserRepository;


public class WorkoutActiveTest {
	UserRepository rep = new UserRepository();
	private EntityManager em;
	WorkoutActiveRepository obj = new WorkoutActiveRepository();
	int id=3;
	@Test
	public void assignWorkoutToUser() {
		
		
		
		User_user user = rep.getUserById(id);
		WorkoutTracker work = new WorkoutTracker(47,"bicy",id,user.getemail());
		obj.assignWorkoutToUser(work);
		
		
		
	}
	
	//@Test
	public void StartWorkout() {
	
	obj.StartWorkout(id);
	}
	
	
	//@Test
	public void EndWorkout() {
		obj.EndWorkout(id);
	}
	
	
	//@Test
	public void Total_Calories(int id) throws ParseException {
		obj.Total_Calories(id);
	}
	
	//@Test
	public void displayrecord() {
		obj.displayrecord();
	}
	
}
