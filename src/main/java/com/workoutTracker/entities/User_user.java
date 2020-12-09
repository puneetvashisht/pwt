package com.workoutTracker.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.ManyToAny;
import javax.persistence.Table;
@Entity 
@Table(name="USER")
public class User_user
{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String email;
	private String password;
	
	private int height;
	private int weight;
	@ManyToOne(cascade = CascadeType.ALL)
	private Role role;
	
	public User_user() {
		super();
	}
	public User_user( String email, String password,  int height, int weight,Role role) {
		super();
		//this.id = id;
		this.email = email;
		this.password = password;
		
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