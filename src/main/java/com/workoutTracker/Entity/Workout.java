<<<<<<< HEAD:workoutTracker/src/main/java/com/workoutTracker/Entity/Workout.java
package com.workoutTracker.Entity;
=======
package com.workoutTracker.entities;

>>>>>>> ad9a6fb17c629d4495c1145fa5ae5d7cb6e30391:workoutTracker/src/main/java/com/workoutTracker/Entity/entities/Workout.java

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
<<<<<<< HEAD:workoutTracker/src/main/java/com/workoutTracker/Entity/Workout.java
@Table(name = "WORKOUT")
public class Workout {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
=======
@Table(name="WORKOUT")
public class Workout {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
>>>>>>> ad9a6fb17c629d4495c1145fa5ae5d7cb6e30391:workoutTracker/src/main/java/com/workoutTracker/Entity/entities/Workout.java
	private int id;
	String title;
	String note;
	int cbpm;

	@ManyToOne(cascade = CascadeType.ALL)
<<<<<<< HEAD:workoutTracker/src/main/java/com/workoutTracker/Entity/Workout.java
	private Category category;

	public Workout() {
	}

	public Workout(String title, String note, int cbpm, Category category) {
=======
private Category category;

	public Workout() {
	}
	public Workout(String title, String note, int cbpm,Category category) {
>>>>>>> ad9a6fb17c629d4495c1145fa5ae5d7cb6e30391:workoutTracker/src/main/java/com/workoutTracker/Entity/entities/Workout.java
		super();
		this.title = title;
		this.note = note;
		this.cbpm = cbpm;
<<<<<<< HEAD:workoutTracker/src/main/java/com/workoutTracker/Entity/Workout.java
		this.category = category;
	}

	public int getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public int getCbpm() {
		return cbpm;
	}

	public void setCbpm(int cbpm) {
		this.cbpm = cbpm;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

=======
		this.category=category;
	}
	
	
	public int getId() {
		return id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public int getCbpm() {
		return cbpm;
	}
	public void setCbpm(int cbpm) {
		this.cbpm = cbpm;
	}
	
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
>>>>>>> ad9a6fb17c629d4495c1145fa5ae5d7cb6e30391:workoutTracker/src/main/java/com/workoutTracker/Entity/entities/Workout.java
	@Override
	public String toString() {
		return "Workout [title=" + title + ", note=" + note + ", cbpm=" + cbpm + "]";
	}
<<<<<<< HEAD:workoutTracker/src/main/java/com/workoutTracker/Entity/Workout.java

=======
	
	
>>>>>>> ad9a6fb17c629d4495c1145fa5ae5d7cb6e30391:workoutTracker/src/main/java/com/workoutTracker/Entity/entities/Workout.java
}