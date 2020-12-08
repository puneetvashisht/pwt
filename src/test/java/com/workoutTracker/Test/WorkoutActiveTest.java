package com.workoutTracker.Test;
import java.text.ParseException;
import javax.persistence.EntityManager;
import com.workoutTracker.Entity.*;
import com.workoutTracker.Repository.WorkoutActiveRepository;
import com.workoutTracker.Repository.UserRepository;

public class WorkoutActiveTest {

	UserRepository rep = new UserRepository();
	WorkoutActiveRepository obj = new WorkoutActiveRepository();

	private EntityManager em;

	int id = 1;

	// @Test
	public void assignWorkoutToUser() {

		User user = rep.findUser("jacky@123.com");
		WorkoutTracker work = new WorkoutTracker(101, "Jogging", id, user.getemail());
		obj.assignWorkoutToUser(work);

	}

	// @Test
	public void StartWorkout() {

		obj.StartWorkout(id);
	}

	// @Test
	public void EndWorkout() {
		obj.EndWorkout(id);
	}

	// @Test
	public void Total_Calories() throws ParseException {
		obj.Total_Calories(id);
	}

	// @Test
	public void displayrecord() {
		obj.displayrecord();
	}

}
