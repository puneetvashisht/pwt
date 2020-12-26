package com.sprint2.personalworkout.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "WorkoutActive")
public class WorkoutActiveTracker {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;
	int workout_id;
	String wokout_title;
	int users_id;
	String user_email;
	String start_date;
	String start_time;
	String end_time;
	int total_calories_burnt;

	public WorkoutActiveTracker() {

	}

	public WorkoutActiveTracker(int workout_id, String wokout_title, int user_id, String user_email) {
		super();

		this.workout_id = workout_id;
		this.wokout_title = wokout_title;
		this.users_id = user_id;
		this.user_email = user_email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getWorkout_id() {
		return workout_id;
	}

	public void setWorkout_id(int workout_id) {
		this.workout_id = workout_id;
	}

	public String getWokout_title() {
		return wokout_title;
	}

	public void setWokout_title(String wokout_title) {
		this.wokout_title = wokout_title;
	}

	public int getUsers_id() {
		return users_id;
	}

	public void setUsers_id(int users_id) {
		this.users_id = users_id;
	}

	public String getUser_email() {
		return user_email;
	}

	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}

	public String getStart_date() {
		return start_date;
	}

	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}

	public String getStart_time() {
		return start_time;
	}

	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}

	public String getEnd_time() {
		return end_time;
	}

	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}

	public int getTotal_calories_burnt() {
		return total_calories_burnt;
	}

	public void setTotal_calories_burnt(int total_calories_burnt) {
		this.total_calories_burnt = total_calories_burnt;
	}



	
}