package com.sprint2.personalworkout.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sprint2.personalworkout.entity.*;
import com.sprint2.personalworkout.exception.UserAlreadyExistsException;
import com.sprint2.personalworkout.exception.UserNotFoundException;
import com.sprint2.personalworkout.repository.*;
import com.sprint2.personalworkout.services.implementation.WorkoutActiveServiceImpl;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
public class WorkoutActiveController {

	@Autowired
	WorkoutActiveServiceImpl activeService;
	@Autowired
	WorkoutActiveRepo activeRepository;

	@PostMapping("/assignWorkout")
	@ApiOperation(value = "Assigning the workout to user", notes = "Enter your user id and workout id")
	public ResponseEntity<String> assignWorkout(@RequestParam("userid") int User_id,
			@RequestParam("workoutid") int workout_id) throws UserNotFoundException, UserAlreadyExistsException {
		ResponseEntity<String> re = null;
		WorkoutTracker workout = activeService.assigningWorkout(User_id, workout_id);
		if (workout != null) {

			re = new ResponseEntity<>("Workout Assigned Successfully!!", HttpStatus.CREATED);
		}
		return re;

	}

	@PatchMapping("/startWorkout/{id}")
	@ApiOperation(value = "Start the workout by id", notes = "Enter the id to start", response = WorkoutTracker.class)
	public ResponseEntity<String> startWorkoutById(@PathVariable("id") int id) throws Exception {

		activeService.startWorkoutById(id);
		return new ResponseEntity<>("Workout Started Successfully!!", HttpStatus.OK);

	}

	@PatchMapping("/endWorkout/{id}")
	@ApiOperation(value = "End the workout by id", notes = "Enter the id to end", response = WorkoutTracker.class)
	public ResponseEntity<String> endWorkoutById(@PathVariable("id") int id) throws Exception {

		activeService.endWorkoutById(id);
		return new ResponseEntity<>("Workout Ended Successfully!!", HttpStatus.OK);

	}

	@GetMapping("/calculateCalories/{id}")
	@ApiOperation(value = "Calculating the calories for user", notes = "Enter the id to calculate", response = WorkoutTracker.class)
	public ResponseEntity<WorkoutTracker> calculateCalories(@PathVariable("id") int id) throws Exception {
		ResponseEntity<WorkoutTracker> re = null;
		WorkoutTracker workout = activeService.calculateCaloriesById(id);

		if (workout != null) {

			re = new ResponseEntity<>(workout, HttpStatus.ACCEPTED);
		} else {
			re = new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return re;

	}

	@GetMapping("/findByEmail")
	@ApiOperation(value = "Get details by Email", notes = "Enter the Email id", response = WorkoutTracker.class)
	public ResponseEntity<WorkoutTracker> getByEmail(@RequestParam("email") String email) throws UserNotFoundException {
		WorkoutTracker found = activeService.findByEmail(email);
		if (found == null) {

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(found, HttpStatus.OK);
		}

	}

	@GetMapping("/findById/{id}")
	@ApiOperation(value = "Get details by id", notes = "Enter the WorkoutActive id", response = WorkoutTracker.class)
	public ResponseEntity<WorkoutTracker> getWorkoutActiveDetailsById(@PathVariable("id") int id)
			throws UserNotFoundException {
		ResponseEntity<WorkoutTracker> response;
		WorkoutTracker userFound = activeService.UserById(id);
		if (userFound != null) {
			response = new ResponseEntity<>(userFound, HttpStatus.OK);
		} else {
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return response;
	}

	@DeleteMapping("/deletebyid/{id}")
	@ApiOperation(value = "Delete by Id", notes = "Enter your WorkoutActive table Id", response = WorkoutTracker.class)
	public ResponseEntity<String> deleteWorkoutActiveDetailsById(@PathVariable("id") int id)
			throws UserNotFoundException {
		activeService.deleteUserById(id);
		return new ResponseEntity<>("Successfully Deleted", HttpStatus.OK);
	}

	@GetMapping("/workoutData")
	@ApiOperation(value = "Get all details in workout", notes = "", response = WorkoutTracker.class)
	public ResponseEntity<List<WorkoutTracker>> getWorkoutActiveDetailsOfAllUser() {

		List<WorkoutTracker> foundall = activeRepository.findAll();
		if (!foundall.isEmpty()) {
			return new ResponseEntity<>(foundall, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
