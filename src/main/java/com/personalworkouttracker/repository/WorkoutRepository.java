package com.personalworkouttracker.repository;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import com.personalworkouttracker.entity.Category;
import com.personalworkouttracker.entity.Workout;

public class WorkoutRepository {

	private EntityManager em;

	public WorkoutRepository() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
		em = emf.createEntityManager();

	}

	public void addWorkout(Workout workout) {
		em.getTransaction().begin();
		Category category = workout.getCategory();
		TypedQuery<Category> query = em.createQuery("select c from Category c where c.cname=:cname", Category.class);

		query.setParameter("cname", workout.getCategory().getCname());
		System.out.println(workout.getCategory().getCname());
		Category existingCategory = query.getSingleResult();

		if (existingCategory.getCname().equals(category.getCname())) {
			workout.setCategory(existingCategory);
		}

		em.persist(workout);
		em.getTransaction().commit();

	}

	public boolean updateWorkout(int id) {

		Workout foundworkout = em.find(Workout.class, id);
		if (foundworkout == null) {
			System.out.println("Invalid Id");
			return false;

		}
		em.getTransaction().begin();
		foundworkout.setTitle("Running");
		em.merge(foundworkout);
		em.getTransaction().commit();

		return true;

	}

	public boolean updateWorkoutByCbpm(int cbpm) {
		/*
		 * em.getTransaction().begin();
		 * 
		 * TypedQuery<Workout> query =
		 * em.createQuery("SELECT u FROM Workout u where cbpm=:cbpm", Workout.class);
		 * 
		 * query.setParameter("cbpm", cbpm); Workout w = query.getSingleResult();
		 * Workout w= em.find(Workout.class,cbpm); System.out.println(w.getCbpm());
		 * if(w.getCbpm()==null) { System.out.println("Invalid Cbpm"); return false;
		 * 
		 * }
		 */
		Workout w = em.find(Workout.class, cbpm);

		w.setTitle("Stretching hands");
		em.merge(w);
		em.getTransaction().commit();
		return true;
	}

	public boolean deleteWorkoutById(int id) {
		Workout foundworkout = em.find(Workout.class, id);
		if (foundworkout == null) {
			System.out.println("Invalid Id");
			return false;

		}
		em.getTransaction().begin();
		em.remove(foundworkout);
		em.getTransaction().commit();
		return true;
	}

	public void deleteWorkoutByTitle(String title) {
		em.getTransaction().begin();

		TypedQuery<Workout> query = em.createQuery("SELECT u FROM Workout u where title=:title ", Workout.class);
		query.setParameter("title", title);
		Workout w = query.getSingleResult();
		em.remove(w);
		em.getTransaction().commit();

	}

	public void findById(int id) {
		TypedQuery<Workout> query = em.createQuery("SELECT u FROM Workout u where u.id=:entityId ", Workout.class);
		query.setParameter("entityId", id);
		Workout w = query.getSingleResult();
		System.out.println(w);

	}

	public void findByTitle(String title) {
		TypedQuery<Workout> query = em.createQuery("SELECT u FROM Workout u where title=:nameparam ", Workout.class);
		query.setParameter("nameparam", title);
		Workout w = query.getSingleResult();
		System.out.println(w);

	}
}