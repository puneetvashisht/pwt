package com.sprint2.personalworkout.services.implementation;

import com.sprint2.personalworkout.entity.Workout;
import com.sprint2.personalworkout.entity.Category;
import com.sprint2.personalworkout.entity.User;
import com.sprint2.personalworkout.repository.UserRepository;
import com.sprint2.personalworkout.services.WorkoutService;
import com.sprint2.personalworkout.repository.WorkoutRepository;
import com.google.common.base.Optional;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint2.personalworkout.exception.UserAlreadyExistsException;
import com.sprint2.personalworkout.exception.UserNotFoundException;
import com.sprint2.personalworkout.exception.WorkoutNotFoundException;

@Service
public class WorkoutServiceImpl implements WorkoutService {

	@Autowired
	WorkoutRepository workoutRepository;

	public WorkoutRepository getWorkoutRepository() {
		return workoutRepository;
	}

	public void setWorkoutRepository(WorkoutRepository workouRepository) {
		this.workoutRepository = workoutRepository;
	}

	@Override
	public Workout addWorkout(Workout workout) throws WorkoutNotFoundException {

		Workout existingWorkout = workoutRepository.findByTitle(workout.getTitle());
		if (existingWorkout != null) {
			throw new WorkoutNotFoundException("Workout Already Exists");
		}
		Category category = workout.getCategory();
		Category existingCategory = workoutRepository.findCategory(workout.getCategory().getCname());
		if (existingCategory.getCname().equals(category.getCname())) {
			workout.setCategory(existingCategory);
			workoutRepository.save(workout);
		}
		return workout;

	}

	@Override
	public Workout findWorkoutById(int id) throws WorkoutNotFoundException {
		Workout existingWorkout = workoutRepository.findWorkout(id);
		if (existingWorkout != null) {
			return existingWorkout;
		} else {
			throw new WorkoutNotFoundException("Workout Not Found");
		}
	}

	@Override
	public Workout findByTitle(String title) throws WorkoutNotFoundException {
		Workout existingWorkout = workoutRepository.findByTitle(title);
		if (existingWorkout != null) {
			return existingWorkout;
		} else {
			throw new WorkoutNotFoundException("Title Not Found");
		}
	}

	@Override
	public List<Workout> findAll() {
		return workoutRepository.findAll();
	}

	@Override
	public void deleteWorkout(int workoutId) throws WorkoutNotFoundException {
		java.util.Optional<Workout> existingWorkout = workoutRepository.findById(workoutId);
		if (!existingWorkout.isPresent()) {
			throw new WorkoutNotFoundException("Workout does not exists!!");
		} else {
			workoutRepository.deleteById(workoutId);
		}
	}

	@Override
	public Workout updateWorkout(Workout workout) throws WorkoutNotFoundException {
		Workout updatedWorkout = workoutRepository.findWorkout(workout.getId());
		if (updatedWorkout != null) {

			Category category = workout.getCategory();
			Category existingCategory = workoutRepository.findCategory(workout.getCategory().getCname());
			if (existingCategory.getCname().equals(category.getCname())) {
				workout.setCategory(existingCategory);
			}
			return workoutRepository.save(workout);

		} else {
			throw new WorkoutNotFoundException("Workout does not exists");
		}

	}

}
