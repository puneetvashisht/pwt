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
	WorkoutActiveRepository obj = new WorkoutActiveRepository();
	
	private EntityManager em;
	
	int id=1;
	
	//@Test
	public void assignWorkoutToUser() {
		
		User_user user = rep.getUserById(id);
		WorkoutTracker work = new WorkoutTracker(101,"Jogging",id,user.getemail());
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
	public void Total_Calories() throws ParseException {
		obj.Total_Calories(id);
	}
	
	//@Test
	public void displayrecord() {
		obj.displayrecord();
	}
	
}
