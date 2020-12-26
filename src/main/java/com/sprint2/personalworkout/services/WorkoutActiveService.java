package com.sprint2.personalworkout.services;

import java.text.ParseException;
import java.util.List;
import com.sprint2.personalworkout.entity.*;
import com.sprint2.personalworkout.exception.UserAlreadyExistsException;
import com.sprint2.personalworkout.exception.UserNotFoundException;
import com.sprint2.personalworkout.exception.WorkoutNotFoundException;


public interface WorkoutActiveService {
	public WorkoutActiveTracker assigningWorkout(int User_id,int workout_id) throws UserNotFoundException, UserAlreadyExistsException, WorkoutNotFoundException;
	public void startWorkoutById(int id) throws UserNotFoundException, WorkoutNotFoundException;
	public void endWorkoutById(int id) throws UserNotFoundException,WorkoutNotFoundException, ParseException;
	
	public WorkoutActiveTracker findByEmail(String email) throws UserNotFoundException;
	public WorkoutActiveTracker UserById(int id) throws UserNotFoundException;
	public void deleteUserById(int id) throws UserNotFoundException;
	public List<WorkoutActiveTracker> findByDate(String date) throws  WorkoutNotFoundException;
}