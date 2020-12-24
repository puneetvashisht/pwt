package com.sprint2.personalworkout.services.implementation;

import java.text.ParseException;
import java.time.LocalTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint2.personalworkout.entity.*;

import com.sprint2.personalworkout.exception.*;

import com.sprint2.personalworkout.repository.WorkoutActiveRepo;
import com.sprint2.personalworkout.services.WorkoutActiveService;

@Service
public class WorkoutActiveServiceImpl implements WorkoutActiveService {

	@Autowired
	WorkoutActiveRepo activeRepository;

	@Override
	public WorkoutTracker assigningWorkout(int User_id, int workout_id)
			throws UserNotFoundException, UserAlreadyExistsException {
		User user = activeRepository.findByUserId(User_id);
		Workout work = activeRepository.findWorkoutById(workout_id);

		if (user != null) {
			String email = user.getEmail();
			WorkoutTracker track = activeRepository.findByEmail(email);
			if (track == null) {

				String workout_title = work.getTitle();

				WorkoutTracker workoutt = new WorkoutTracker(workout_id, workout_title, User_id, email);
				activeRepository.save(workoutt);
				return workoutt;

			} else if ((track.getStart_datetime() != null && track.getEnd_datetime() != null)) {
				String workout_title = work.getTitle();

				WorkoutTracker workoutt = new WorkoutTracker(workout_id, workout_title, User_id, email);
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
	public void startWorkoutById(int id) throws UserNotFoundException {
		Optional<WorkoutTracker> track = activeRepository.findById(id);
		if (track.isPresent()) {
			WorkoutTracker isFound = track.get();
			LocalTime lt = LocalTime.now();
			String as = lt.toString();
			isFound.setStart_datetime(as);
			activeRepository.save(isFound);
		} else {
			throw new UserNotFoundException("User not Assigned to Workout!!");
		}

	}

	@Override
	public void endWorkoutById(int id) throws UserNotFoundException {
		Optional<WorkoutTracker> track = activeRepository.findById(id);
		if (track.isPresent()) {
			WorkoutTracker isFound = track.get();
			if (isFound.getStart_datetime() != null) {
				LocalTime lt = LocalTime.now();
				String as = lt.toString();
				isFound.setEnd_datetime(as);
				activeRepository.save(isFound);
			} else {
				throw new UserNotFoundException("Workout not Started..Please start the workout!!");

			}
		} else {
			throw new UserNotFoundException("User not Assigned to Workout!!");
		}
	}

	@Override
	public WorkoutTracker calculateCaloriesById(int id) throws ParseException, UserNotFoundException {

		Optional<WorkoutTracker> track = activeRepository.findById(id);
		if (track.isPresent()) {
			WorkoutTracker isFound = track.get();
			if (isFound.getStart_datetime() != null && isFound.getEnd_datetime() != null) {
				LocalTime start = LocalTime.parse(isFound.getStart_datetime());
				LocalTime end = LocalTime.parse(isFound.getEnd_datetime());
				int difference = ((end.getHour() - start.getHour()) * 60) + (end.getMinute() - start.getMinute());
				isFound.setTotal_calories_burnt(assignCalories(difference));
				activeRepository.save(isFound);
				return isFound;
			} else {
				throw new UserNotFoundException("Workout not Started / Workout not Ended!!");

			}
		} else {
			throw new UserNotFoundException("Either Invalid User");
		}
	}

	public WorkoutTracker findByEmail(String email) throws UserNotFoundException {
		WorkoutTracker found = activeRepository.findByEmail(email);
		if (found != null) {
			return found;
		} else {
			throw new UserNotFoundException("User Not Found");
		}

	}

	public WorkoutTracker UserById(int id) throws UserNotFoundException {
		Optional<WorkoutTracker> existingUser = activeRepository.findById(id);
		if (existingUser != null) {
			return existingUser.get();
		}
		else {
			throw new UserNotFoundException("User not found");
		}

	}

	public void deleteUserById(int id) throws UserNotFoundException {
		Optional<WorkoutTracker> existingUser = activeRepository.findById(id);
		if (!existingUser.isPresent()) {
			throw new UserNotFoundException("User does not exists!!");
		} else {
			activeRepository.deleteById(id);
		}
	}

	public int assignCalories(long value) {
		int count = 0;

		if (value > 2) {

			if (value > 60) {
				count = 25;
			} else if (value > 40) {
				count = 17;
			} else if (value > 20) {
				count = 12;
			} else if (value >= 3) {
				count = 6;
			}

		} else {
			count = 1;

		}
		return count;

	}

}
