package com.sprint2.personalworkout.services;

import java.util.List;
import java.util.Optional;

import com.sprint2.personalworkout.entity.Workout;

public interface WorkoutService {

	 Workout addWorkout(Workout workout);

	 Optional<Workout> findWorkoutById(int id);

	 Workout findByTitle(String title);

	 List<Workout> findAll();

	 Workout updateWorkout(Workout workout);

	 void deleteWorkout(int id);
}
