<<<<<<< HEAD:workoutTracker/src/main/java/com/workoutTracker/Entity/Role.java
package com.workoutTracker.Entity;
=======
package com.workoutTracker.entities;
>>>>>>> ad9a6fb17c629d4495c1145fa5ae5d7cb6e30391:workoutTracker/src/main/java/com/workoutTracker/Entity/entities/Role.java

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
<<<<<<< HEAD:workoutTracker/src/main/java/com/workoutTracker/Entity/Role.java
	int id;
	String role_name;

	public Role() {
		super();
	}

	public Role(String role_name) {
		super();

		this.role_name = role_name;
	}

	public String getRole_name() {
		return role_name;
	}

	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}

=======
    int id;
	String role_name;
	
	public Role()
	{
		super();
	}
	public Role( String role_name) {
		super();
		//this.id = id;
		this.role_name = role_name;
	}
>>>>>>> ad9a6fb17c629d4495c1145fa5ae5d7cb6e30391:workoutTracker/src/main/java/com/workoutTracker/Entity/entities/Role.java
	@Override
	public String toString() {
		return "Role [id=" + id + ", role_name=" + role_name + "]";
	}
<<<<<<< HEAD:workoutTracker/src/main/java/com/workoutTracker/Entity/Role.java

=======
	
>>>>>>> ad9a6fb17c629d4495c1145fa5ae5d7cb6e30391:workoutTracker/src/main/java/com/workoutTracker/Entity/entities/Role.java
}
