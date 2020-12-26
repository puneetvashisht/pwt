package com.sprint2.personalworkout.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import com.sprint2.personalworkout.entity.Category;
import com.sprint2.personalworkout.entity.Workout;

@EnableJpaRepositories
public interface WorkoutRepository extends JpaRepository<Workout, Integer> {

	public Workout findByTitle(String title);

	@Query("Select w from Workout w where w.title=?1")
	public Optional<Workout> findByUpdateTitle(String title);

	@Query("select c from Category c where c.cname=?1")
	public Category findCategory(String cname);

	@Query("select w from Workout w where w.id=?1")
	public Workout findWorkout(int id);

	@Query("select w from Workout w where w.id=?1")
	public Optional<Workout> findWorkouts(int id);

	public Workout save(Optional<Workout> updatedWorkout);

}
