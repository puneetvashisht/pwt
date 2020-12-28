package com.sprint2.personalworkout.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.sprint2.personalworkout.entity.Workout;
import com.sprint2.personalworkout.entity.WorkoutActiveTracker;

@Repository
public interface WorkoutActiveRepo extends JpaRepository<WorkoutActiveTracker, Integer> {

	@Query("select w from WorkoutActiveTracker w where w.userEmail=?1")
	 WorkoutActiveTracker findByEmail(String email);

	@Query("select w from Workout w where w.title=?1")
	 Workout getCbpm(String name);

	@Query("select w from WorkoutActiveTracker w where w.startDate=?1")
	 List<WorkoutActiveTracker> findByDate(String date);

}