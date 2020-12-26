package com.sprint2.personalworkout.services.implementation;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sprint2.personalworkout.entity.*;
import com.sprint2.personalworkout.exception.*;

import com.sprint2.personalworkout.repository.WorkoutActiveRepo;
import com.sprint2.personalworkout.services.UserService;
import com.sprint2.personalworkout.services.WorkoutActiveService;
import com.sprint2.personalworkout.services.WorkoutService;

@Service
public class WorkoutActiveServiceImpl implements WorkoutActiveService {

	@Autowired
	WorkoutActiveRepo activeRepository;
	
	@Autowired
	UserService userService;
	
	@Autowired
	WorkoutService workoutService;

	@Override
	public WorkoutActiveTracker assigningWorkout(int User_id, int workout_id)	throws UserNotFoundException, UserAlreadyExistsException, WorkoutNotFoundException {
		Optional<User> user = userService.findUserById(User_id);
		Optional<Workout> work = workoutService.findWorkoutById(workout_id);
		if (user != null) {
			User objUser=user.get();
			String email = objUser.getEmail();
			WorkoutActiveTracker track = activeRepository.findByEmail(email);
			if (track == null) {
				Workout workoutObj1=work.get();
				String workout_title = workoutObj1.getTitle();
				WorkoutActiveTracker workoutt = new WorkoutActiveTracker(workout_id, workout_title, User_id, email);
				activeRepository.save(workoutt);
				return workoutt;
			} else if ((track.getStart_time() != null && track.getEnd_time() != null)) {
				Workout workoutObj=work.get();
				String workout_title = workoutObj.getTitle();
				WorkoutActiveTracker workoutt = new WorkoutActiveTracker(workout_id, workout_title, User_id, email);
				activeRepository.save(workoutt);
				return workoutt;
			} else {
				throw new UserAlreadyExistsException("Workout Already assigned");
			}
		} else {
			throw new UserNotFoundException("User not Exists!!");
		}
	}

	@Override
	public void startWorkoutById(int id) throws UserNotFoundException, WorkoutNotFoundException {
		Optional<WorkoutActiveTracker> track = activeRepository.findById(id);
		if (track.isPresent()) {
			WorkoutActiveTracker isFound = track.get();
			if (isFound.getStart_time() == null) {
				LocalTime lt = LocalTime.now();
				String as = lt.toString();
				LocalDate date = LocalDate.now();
				String date1 = date.toString();
				isFound.setStart_date(date1);
				isFound.setStart_time(as);
				activeRepository.save(isFound);
			} else {
				throw new WorkoutNotFoundException("Workout Already Started");
			}

		} else {
			throw new UserNotFoundException("User not Assigned to Workout!!");
		}

	}
	@Override
	public void endWorkoutById(int id) throws ParseException,UserNotFoundException, WorkoutNotFoundException {
		Optional<WorkoutActiveTracker> track = activeRepository.findById(id);
		if (track.isPresent()) {
			WorkoutActiveTracker isFound = track.get();
			if (isFound.getStart_time() != null) {
				if (isFound.getEnd_time() == null) {
					LocalTime end = LocalTime.now();
					String as = end.toString();
					isFound.setEnd_time(as);
					activeRepository.save(isFound);
					LocalTime start = LocalTime.parse(isFound.getStart_time());
					int difference = ((end.getHour() - start.getHour()) * 60) + (end.getMinute() - start.getMinute());
					String name = isFound.getWokout_title();
					Workout work = activeRepository.getCbpm(name);
					isFound.setTotal_calories_burnt(difference*work.getCbpm());
					activeRepository.save(isFound);
					
				} else {
					throw new WorkoutNotFoundException("Workout Already Ended");
				}
			} else {
				throw new WorkoutNotFoundException("Workout not Started..Please start the workout!!");

			}
		} else {
			throw new UserNotFoundException("User not Assigned to Workout!!");
		}
	}

	
	@Override
	public WorkoutActiveTracker findByEmail(String email) throws UserNotFoundException {
		WorkoutActiveTracker found = activeRepository.findByEmail(email);
		if (found != null) {
			return found;
		} else {
			throw new UserNotFoundException("User Not Found");
		}

	}

	@Override
	public WorkoutActiveTracker UserById(int id) throws UserNotFoundException {
		Optional<WorkoutActiveTracker> existingUser = activeRepository.findById(id);
		if (existingUser != null) {
			return existingUser.get();
		} else {
			throw new UserNotFoundException("User not found");
		}

	}

	@Override
	public void deleteUserById(int id) throws UserNotFoundException {
		Optional<WorkoutActiveTracker> existingUser = activeRepository.findById(id);
		if (!existingUser.isPresent()) {
			throw new UserNotFoundException("User does not exists!!");
		} else {
			activeRepository.deleteById(id);
		}
	}

	
	public List<WorkoutActiveTracker> findByDate(String date) throws  WorkoutNotFoundException{
		List<WorkoutActiveTracker>  details =  activeRepository.findByDate(date);
		if(details.isEmpty()) {
			throw new WorkoutNotFoundException("No workouts has been assigned or started in this date");
		}
		else {
			return details;
		}
		
	}
}
