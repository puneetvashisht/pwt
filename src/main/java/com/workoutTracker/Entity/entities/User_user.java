<<<<<<< HEAD:workoutTracker/src/main/java/com/workoutTracker/Entity/User.java
package com.workoutTracker.Entity;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
=======
package com.workoutTracker.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
>>>>>>> ad9a6fb17c629d4495c1145fa5ae5d7cb6e30391:workoutTracker/src/main/java/com/workoutTracker/Entity/entities/User_user.java
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
<<<<<<< HEAD:workoutTracker/src/main/java/com/workoutTracker/Entity/User.java
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.ManyToAny;

@Entity 
//@Table(uniqueConstraints={@UniqueConstraint(columnNames={"email"})})
public class User
{	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name="EMAIL",unique=true)
=======

import org.hibernate.annotations.ManyToAny;
import javax.persistence.Table;
@Entity 
@Table(name="USER")
public class User_user
{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
>>>>>>> ad9a6fb17c629d4495c1145fa5ae5d7cb6e30391:workoutTracker/src/main/java/com/workoutTracker/Entity/entities/User_user.java
	private String email;
	private String password;
	
	private int height;
	private int weight;
<<<<<<< HEAD:workoutTracker/src/main/java/com/workoutTracker/Entity/User.java
	@Enumerated(EnumType.STRING)
	@ManyToOne(cascade = CascadeType.PERSIST)
	private Role role;
	
	public User() { 
		super();
	}
	public User( String email, String password,  int height, int weight,Role role) {
=======
	@ManyToOne(cascade = CascadeType.ALL)
	private Role role;
	
	public User_user() {
		super();
	}
	public User_user( String email, String password,  int height, int weight,Role role) {
>>>>>>> ad9a6fb17c629d4495c1145fa5ae5d7cb6e30391:workoutTracker/src/main/java/com/workoutTracker/Entity/entities/User_user.java
		super();
		//this.id = id;
		this.email = email;
		this.password = password;
<<<<<<< HEAD:workoutTracker/src/main/java/com/workoutTracker/Entity/User.java
		this.height = height;
		this.weight = weight; 
		this.role=role;
	}  
	
=======
		
		this.height = height;
		this.weight = weight; 
		this.role=role;
	} 
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
>>>>>>> ad9a6fb17c629d4495c1145fa5ae5d7cb6e30391:workoutTracker/src/main/java/com/workoutTracker/Entity/entities/User_user.java
	public String getemail() {
		return email;
	}
	public void setemail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	@Override
	public String toString() {
		return "User_user [email=" + email + ", password=" + password + ", height=" + height + ", weight=" + weight
				+ "]";
	}
	
	
}