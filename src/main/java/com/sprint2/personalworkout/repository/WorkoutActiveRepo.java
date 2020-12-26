package com.sprint2.personalworkout.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sprint2.personalworkout.entity.User;
import com.sprint2.personalworkout.entity.Workout;
import com.sprint2.personalworkout.entity.WorkoutTracker;



@Repository
public interface WorkoutActiveRepo extends JpaRepository<WorkoutTracker, Integer> {

	@Query("select w from WorkoutTracker w where w.user_email=?1")
	public WorkoutTracker findByEmail(String email);

	
	
//	@Query("select u from User u where u.id=?1")
//	public User findByUserId(int id);
	
	@Query("select w from Workout w where w.id=?1")
	public Workout findWorkoutById(int id);
	
	
	
}
