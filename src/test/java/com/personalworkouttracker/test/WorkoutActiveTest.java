package com.personalworkouttracker.test;

import static org.junit.Assert.*;

import java.text.ParseException;

import com.personalworkouttracker.entity.*;
import com.personalworkouttracker.entity.User;
import com.personalworkouttracker.exception.ValidationException;
import com.personalworkouttracker.repository.UserRepository;
import com.personalworkouttracker.repository.WorkoutActiveRepository;
import com.personalworkouttracker.service.WokoutActiveService;

import org.junit.Test;

public class WorkoutActiveTest {

	UserRepository rep = new UserRepository();
	WokoutActiveService serv = new WokoutActiveService();
	WorkoutActiveRepository obj = new WorkoutActiveRepository();
	int id = 5;

	 //@Test
	public void testAssignWorkoutToUser() {

		User user = rep.getUserById(id);
		
		WorkoutTracker work = new WorkoutTracker(102, "weight lifting", id, user.getEmail());
		obj.assignWorkoutToUser(work);

	}


	 //@Test
	public void testStartWorkout() {
		boolean found = obj.StartWorkout(8);
         assertTrue(found);
	
	}
	
	//@Test
	public void testStartWorkoutFail() {
		boolean notFound= obj.StartWorkout(8);
		assertFalse(notFound);
	}

	
     //@Test
	public void testEndWorkout() {
		boolean end = obj.EndWorkout(8);
		assertTrue(end);
	}
	
	//@Test
	public void testEndWorkoutFail() {
		boolean notEnd = obj.EndWorkout(8);
		assertFalse(notEnd);
	}

	

	 @Test
	public void testCalculateCalories() throws ParseException {
		boolean success = serv.calculate_calories(1);
		assertTrue(success);
	}
	
	//@Test
		public void testCalculateCaloriesFail() throws ParseException {
			boolean unsuccess = serv.calculate_calories(8);
			assertFalse(unsuccess);
			
		}


	// @Test
	public void testDisplayrecord() {
		obj.displayrecord();
	}

	//@Test
	public void displayParticular() {
	obj.displayByEmail("Ranjith@123.com");
	  
	}
	
	

}
