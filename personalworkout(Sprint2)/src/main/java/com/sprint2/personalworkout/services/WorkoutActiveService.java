package com.sprint2.personalworkout.services;

import java.text.ParseException;

import com.sprint2.personalworkout.entity.*;
import com.sprint2.personalworkout.exception.UserAlreadyExistsException;
import com.sprint2.personalworkout.exception.UserNotFoundException;


public interface WorkoutActiveService {
	public WorkoutTracker assigningWorkout(int User_id,int workout_id) throws UserNotFoundException,UserAlreadyExistsException;
	public void startWorkoutById(int id) throws UserNotFoundException;
	public void endWorkoutById(int id) throws UserNotFoundException;
	public WorkoutTracker calculateCaloriesById(int id) throws ParseException, UserNotFoundException;
	public WorkoutTracker findByEmail(String email) throws UserNotFoundException;
	public WorkoutTracker UserById(int id) throws UserNotFoundException;
	public void deleteUserById(int id) throws UserNotFoundException;
}
