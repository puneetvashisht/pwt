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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	int workoutId;
	String workoutTitle;
	int userId;
	String userEmail;
	String startDate;
	String startTime;
	String endTime;
	int totalCaloriesBurnt;

	public WorkoutActiveTracker() {

	}

	public WorkoutActiveTracker(int workoutId, String workoutTitle, int userId, String userEmail) {
		super();

		this.workoutId = workoutId;
		this.workoutTitle = workoutTitle;
		this.userId = userId;
		this.userEmail = userEmail;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getWorkoutId() {
		return workoutId;
	}

	public void setWorkoutId(int workoutId) {
		this.workoutId = workoutId;
	}

	public String getWorkoutTitle() {
		return workoutTitle;
	}

	public void setWorkoutTitle(String workoutTitle) {
		this.workoutTitle = workoutTitle;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public int getTotalCaloriesBurnt() {
		return totalCaloriesBurnt;
	}

	public void setTotalCaloriesBurnt(int totalCaloriesBurnt) {
		this.totalCaloriesBurnt = totalCaloriesBurnt;
	}



	
}