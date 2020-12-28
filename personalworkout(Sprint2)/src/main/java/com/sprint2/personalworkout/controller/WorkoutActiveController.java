package com.sprint2.personalworkout.controller;

import java.text.ParseException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
import com.sprint2.personalworkout.exception.WorkoutNotFoundException;
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
	private static Logger logger = LogManager.getLogger(WorkoutActiveController.class);

	/**
	 * This method is used to assign the workout to the user
	 * @param userId
	 * @param workoutId
	 * @return
	 * @throws UserNotFoundException
	 * @throws UserAlreadyExistsException
	 * @throws WorkoutNotFoundException
	 */
	
	@PostMapping("/activeWorkouts")
	@ApiOperation(value = "Assigning the workout to user", notes = "Enter your user id and workout id")
	public ResponseEntity<String> assignWorkout(@RequestParam("userid") int userId,
			@RequestParam("workoutId") int workoutId)
			throws UserNotFoundException, UserAlreadyExistsException, WorkoutNotFoundException {
		ResponseEntity<String> re = null;
		WorkoutActiveTracker workout = activeService.assigningWorkout(userId, workoutId);
		if (workout != null) {
			re = new ResponseEntity<>("Workout Assigned Successfully!!", HttpStatus.CREATED);
		}
		return re;
	}

	/**
	 * This method is used to start workout for a user
	 * @param id
	 * @return
	 * @throws UserNotFoundException
	 * @throws WorkoutNotFoundException
	 */
	
	@PatchMapping("/activeworkouts/startworkout/{id}")
	@ApiOperation(value = "Start the workout by id", notes = "Enter the id to start", response = WorkoutActiveTracker.class)
	public ResponseEntity<String> startWorkoutById(@PathVariable("id") int id)throws UserNotFoundException, WorkoutNotFoundException {
		logger.info("Recieved id on path:");
		activeService.startWorkoutById(id);
		return new ResponseEntity<>("Workout Started Successfully!!", HttpStatus.OK);
	}

	/**
	 * This method is used to end workout for a user
	 * @param id
	 * @return
	 * @throws ParseException
	 * @throws UserNotFoundException
	 * @throws WorkoutNotFoundException
	 */
	
	@PatchMapping("/activeworkouts/endworkout/{id}")
	@ApiOperation(value = "End the workout by id", notes = "Enter the id to end", response = WorkoutActiveTracker.class)
	public ResponseEntity<String> endWorkoutById(@PathVariable("id") int id){
		activeService.endWorkoutById(id);
		return new ResponseEntity<>("Workout Ended Successfully!!", HttpStatus.OK);
	}

	/**
	 * This method is used to get the 
	 * @param email
	 * @return
	 * @throws UserNotFoundException
	 */
	
	@GetMapping("/activeworkouts/findbyemail")
	@ApiOperation(value = "Get details by Email", notes = "Enter the Email id", response = WorkoutActiveTracker.class)
	public ResponseEntity<WorkoutActiveTracker> getByEmail(@RequestParam("email") String email)
			throws UserNotFoundException {
		WorkoutActiveTracker found = activeService.findByEmail(email);
		if (found == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(found, HttpStatus.OK);
		}
	}

	/**
	 * This method is used to get active workout details of a user by date
	 * @param date
	 * @return
	 * @throws WorkoutNotFoundException
	 */
	
	@GetMapping("/findbydate")
	@ApiOperation(value = "Get all details by date", notes = "", response = WorkoutActiveTracker.class)
	public ResponseEntity<List<WorkoutActiveTracker>> getWorkoutActiveDetailsbyDate(@RequestParam("date") String date)
			throws WorkoutNotFoundException {

		List<WorkoutActiveTracker> foundall = activeService.findByDate(date);
		if (!foundall.isEmpty()) {
			return new ResponseEntity<>(foundall, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * This method is used to get active workout details of user by Id
	 * @param id
	 * @return
	 * @throws UserNotFoundException
	 */
	
	@GetMapping("/activeworkouts/findbyid/{id}")
	@ApiOperation(value = "Get details by id", notes = "Enter the WorkoutActive id", response = WorkoutActiveTracker.class)
	public ResponseEntity<WorkoutActiveTracker> getWorkoutActiveDetailsById(@PathVariable("id") int id)
			throws UserNotFoundException {
		ResponseEntity<WorkoutActiveTracker> response;
		WorkoutActiveTracker userFound = activeService.findById(id);
		if (userFound != null) {
			response = new ResponseEntity<>(userFound, HttpStatus.OK);
		} else {
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return response;
	}

	/**
	 * This method is used to delete workokut details of a user by Id
	 * @param id
	 * @return
	 * @throws UserNotFoundException
	 */
	
	@DeleteMapping("/activeworkouts/{id}")
	@ApiOperation(value = "Delete by Id", notes = "Enter your WorkoutActive table Id", response = WorkoutActiveTracker.class)
	public ResponseEntity<String> deleteWorkoutActiveDetailsById(@PathVariable("id") int id)
			throws UserNotFoundException {
		activeService.deleteUserById(id);
		return new ResponseEntity<>("Successfully Deleted", HttpStatus.OK);
	}

	/**
	 * This method is used to get workout Details of all user
	 * @return
	 */
	
	@GetMapping("/activeworkouts/viewall")
	@ApiOperation(value = "Get all details in workout", notes = "", response = WorkoutActiveTracker.class)
	public ResponseEntity<List<WorkoutActiveTracker>> getWorkoutActiveDetailsOfAllUser() {

		List<WorkoutActiveTracker> foundall = activeRepository.findAll();
		if (!foundall.isEmpty()) {
			return new ResponseEntity<>(foundall, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
