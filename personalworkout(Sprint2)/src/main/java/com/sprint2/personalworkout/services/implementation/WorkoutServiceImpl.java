package com.sprint2.personalworkout.services.implementation;

import com.sprint2.personalworkout.entity.Workout;
import com.sprint2.personalworkout.entity.Category;
import com.sprint2.personalworkout.services.WorkoutService;
import com.sprint2.personalworkout.repository.WorkoutRepository;
import java.util.Optional;
import java.util.List;
import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sprint2.personalworkout.exception.WorkoutAlreadyExistsException;
import com.sprint2.personalworkout.exception.WorkoutNotFoundException;

@Service
public class WorkoutServiceImpl implements WorkoutService {

	@Autowired
	WorkoutRepository workoutRepository;
	private static Logger logger = LogManager.getLogger(WorkoutServiceImpl.class);


	@Override
	public Workout addWorkout(Workout workout) throws WorkoutAlreadyExistsException {

		Workout existingWorkout = workoutRepository.findByTitle(workout.getTitle());
		if (existingWorkout != null) {
			logger.warn("Workout Already Exists");
			throw new WorkoutAlreadyExistsException("Workout Already Exists");
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
	public Optional<Workout> findWorkoutById(int id) throws WorkoutNotFoundException {
		Optional<Workout> existingWorkout = workoutRepository.findById(id);
		if (existingWorkout.isPresent()) {
			return existingWorkout;

		} else {
			logger.warn("Workout Not Found");
			throw new WorkoutNotFoundException("Workout Not Found");
		}
	}

	@Override
	public Workout findByTitle(String title) throws WorkoutNotFoundException {
		Workout existingWorkout = workoutRepository.findByTitle(title);
		if (existingWorkout != null) {
			return existingWorkout;
		} else {
			logger.warn("Title Not Found");
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
			logger.warn("Workout does not exists!!");
			throw new WorkoutNotFoundException("Workout does not exists!!");
		} else {
			workoutRepository.deleteById(workoutId);
		}
	}

	@Override
	@Transactional
	public Workout updateWorkout(Workout workout) throws WorkoutNotFoundException {
		Workout udpatedWorkout = workoutRepository.findWorkout(workout.getId());

		if (workout.getTitle() != null) {
			udpatedWorkout.setTitle(workout.getTitle());
		}
		if (workout.getNote() != null) {
			udpatedWorkout.setNote(workout.getNote());
		}
		return udpatedWorkout;
	}

}
