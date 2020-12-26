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
	public WorkoutActiveTracker assigningWorkout(int userId, int workoutId)	throws UserNotFoundException, UserAlreadyExistsException, WorkoutNotFoundException {
		Optional<User> user = userService.findUserById(userId);
		Optional<Workout> work = workoutService.findWorkoutById(workoutId);
		Workout workoutObj;
		String workoutTitle;
		WorkoutActiveTracker workoutt ;
		if (user.isPresent()) {
			User objUser=user.get();
			String email = objUser.getEmail();
			WorkoutActiveTracker track = activeRepository.findByEmail(email);
			if (track == null) {				
				Workout workoutObj1=work.get();
				workoutTitle = workoutObj1.getTitle();
				 workoutt = new WorkoutActiveTracker(workoutId, workoutTitle, userId, email);
				activeRepository.save(workoutt);
				return workoutt;
			} else if ((track.getStartTime() != null && track.getEndTime() != null)) {
				if(work.isPresent()) {
				 workoutObj=work.get();
				workoutTitle = workoutObj.getTitle();
				 workoutt = new WorkoutActiveTracker(workoutId, workoutTitle, userId, email);
				activeRepository.save(workoutt);
				return workoutt;}
				return null;
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
			if (isFound.getStartTime() == null) {
				LocalTime lt = LocalTime.now();
				String as = lt.toString();
				LocalDate date = LocalDate.now();
				String date1 = date.toString();
				isFound.setStartDate(date1);
				isFound.setStartTime(as);
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
			if (isFound.getStartTime() != null) {
				if (isFound.getEndTime() == null) {
					LocalTime end = LocalTime.now();
					String as = end.toString();
					isFound.setEndTime(as);
					activeRepository.save(isFound);
					LocalTime start = LocalTime.parse(isFound.getStartTime());
					int difference = ((end.getHour() - start.getHour()) * 60) + (end.getMinute() - start.getMinute());
					String name = isFound.getWorkoutTitle();
					Workout work = activeRepository.getCbpm(name);
					isFound.setTotalCaloriesBurnt(difference*work.getCbpm());
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
	public WorkoutActiveTracker findById(int id) throws UserNotFoundException {
		Optional<WorkoutActiveTracker> existingUser = activeRepository.findById(id);
		if (existingUser.isPresent()) {
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
