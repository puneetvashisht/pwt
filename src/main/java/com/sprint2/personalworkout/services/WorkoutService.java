package com.sprint2.personalworkout.services;
import com.sprint2.personalworkout.exception.WorkoutAlreadyExistsException;
import com.sprint2.personalworkout.exception.WorkoutNotFoundException;
import com.sprint2.personalworkout.entity.Workout;
import java.util.List;
import java.util.Optional;

public interface WorkoutService {
	
	public Workout addWorkout(Workout workout) throws WorkoutAlreadyExistsException;
	
	public Optional<Workout> findWorkoutById(int id) throws WorkoutNotFoundException;
	
	public Workout findByTitle(String title)throws WorkoutNotFoundException;
	
	public List<Workout> findAll();
	
	public Workout updateWorkout(Workout workout)throws WorkoutNotFoundException ;

	public void deleteWorkout(int id) throws WorkoutNotFoundException;
}
