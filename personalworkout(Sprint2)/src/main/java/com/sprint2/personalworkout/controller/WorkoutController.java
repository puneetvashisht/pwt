package com.sprint2.personalworkout.controller;

import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import com.sprint2.personalworkout.repository.WorkoutRepository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.sprint2.personalworkout.entity.Workout;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.sprint2.personalworkout.services.WorkoutService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import com.sprint2.personalworkout.exception.WorkoutAlreadyExistsException;
import com.sprint2.personalworkout.exception.WorkoutNotFoundException;

@RestController
@RequestMapping("/api")
public class WorkoutController {

	@Autowired
	WorkoutRepository workoutRepository;
	@Autowired
	WorkoutService workoutService;

	/**
	 * This method is used to add the workouts
	 * @param workout
	 * @return
	 * @throws WorkoutAlreadyExistsException
	 */
	
	@PostMapping("/workouts")
	@ApiOperation(value = "Adding the Workout", notes = "Enter all values to add the workout", response = Workout.class)
	public ResponseEntity<String> addWorkout(@RequestBody Workout workout) throws WorkoutAlreadyExistsException {
		Workout workouts = workoutService.addWorkout(workout);
		if (workouts != null) {
			return new ResponseEntity<>("successfully registered workout!!", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>("Workout Not Added", HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * This method is used to get all the workouts
	 * @return
	 */
	
	@GetMapping("/workouts")
	@ApiOperation(value = "View all workouts", notes = "", response = Workout.class)
	public ResponseEntity<List<Workout>> getAllWorkouts() {
		List<Workout> workouts = workoutService.findAll();
		if (!workouts.isEmpty()) {
			return new ResponseEntity<>(workouts, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * This method is used to get the workout by Id
	 * @param id
	 * @return
	 * @throws WorkoutNotFoundException
	 */
	
	@GetMapping("/workouts/{id}")
	@ApiOperation(value = "Getting the Workout By Id", notes = "Enter your WorkoutId", response = Workout.class)
	public ResponseEntity<Optional<Workout>> getWorkoutById(
			@ApiParam(value = "Enter your Id", required = true) @PathVariable("id") int id)
			throws WorkoutNotFoundException {
		ResponseEntity<Optional<Workout>> re;
		Optional<Workout> workout = workoutService.findWorkoutById(id);
		if (!workout.isPresent()) {
			re = new ResponseEntity<>(HttpStatus.NOT_FOUND);			
		} else {
			re = new ResponseEntity<>(workout, HttpStatus.OK);
		}
		return re;
	}

	/**
	 * This method is used to get workout By Title
	 * @param title
	 * @return
	 * @throws WorkoutNotFoundException
	 */
	
	@GetMapping("/workouts/title")
	@ApiOperation(value = "Getting the Workout By Title", notes = "Enter your Title", response = Workout.class)
	public ResponseEntity<Workout> getWorkoutByTitle(@ApiParam(value = "Enter your Email", required = true) @RequestParam("title") String title)throws WorkoutNotFoundException {
		Workout existingWorkout = workoutService.findByTitle(title);
		if (existingWorkout == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(existingWorkout, HttpStatus.OK);
	}

	/**
	 * This method is used to delete workout By Id
	 * @param id
	 * @return
	 * @throws WorkoutNotFoundException
	 */
	
	@DeleteMapping("/workouts/{id}")
	@ApiOperation(value = "Deleting a Workout By Id", notes = "Enter your WorkoutId", response = Workout.class)
	public ResponseEntity<String> deleteWorkout(
			@ApiParam(value = "Enter your userId", required = true) @PathVariable("id") int id)
			throws WorkoutNotFoundException {
		workoutService.deleteWorkout(id);
		return new ResponseEntity<>("Successfully Deleted", HttpStatus.OK);
	}

	/**
	 * This method is used to update workouts
	 * @param workoutObj
	 * @return
	 * @throws WorkoutNotFoundException
	 */
	
	@PutMapping("/workouts")
	@ApiOperation(value = "Editing a Workout", notes = "Enter your all values including edited values", response = Workout.class)
	public ResponseEntity<String> updateWorkout(
			@ApiParam(value = "Enter your Details", required = true) @RequestBody Workout workoutObj)
			throws WorkoutNotFoundException {
		 workoutService.updateWorkout(workoutObj);
		return new ResponseEntity<>("Workout updated", HttpStatus.CREATED);
	}
}
