package com.sprint2.personalworkout.services;

import java.util.List;

import com.sprint2.personalworkout.entity.WorkoutActiveTracker;

public interface WorkoutActiveService {
	 WorkoutActiveTracker assigningWorkout(int userId, int workoutId);

	 void startWorkoutById(int id);

	 void endWorkoutById(int id);

	 WorkoutActiveTracker findByEmail(String email);

	 WorkoutActiveTracker findById(int id);

	 void deleteUserById(int id);

	 List<WorkoutActiveTracker> findByDate(String date);
}