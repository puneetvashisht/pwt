package com.cpg;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


@Entity

public class WorkoutTracker {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
     int id;
     int workout_id;
     String wokout_title;
     int user_id;
     String user_email;
     String start_datetime;
     String end_datetime;
     String tota_calories_burnt;


    public WorkoutTracker()
    {
        
    }

	public WorkoutTracker(int workout_id, String wokout_title, int user_id, String user_email) {
		super();
	
		this.workout_id = workout_id;
		this.wokout_title = wokout_title;
		this.user_id = user_id;
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


	public int getUser_id() {
		return user_id;
	}


	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}


	public String getUser_email() {
		return user_email;
	}


	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}


	public String getStart_datetime() {
		return start_datetime;
	}


	public void setStart_datetime(String start_datetime) {
		this.start_datetime = start_datetime;
	}


	public String getEnd_datetime() {
		return end_datetime;
	}


	public void setEnd_datetime(String end_datetime) {
		this.end_datetime = end_datetime;
	}


	public String getTota_calories_burnt() {
		return tota_calories_burnt;
	}


	public void setTota_calories_burnt(String tota_calories_burnt) {
		this.tota_calories_burnt = tota_calories_burnt;
	}

public long calculateCalories(String a,String b) throws ParseException {
	SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
	Date date1 = format.parse(a);
	Date date2 = format.parse(b);
	long difference = date2.getTime() - date1.getTime();
	long result=difference/1000;
	return result;
}
	@Override
	public String toString() {
		return "WorkoutTracker [id=" + id + ", workout_id=" + workout_id + ", wokout_title=" + wokout_title
				+ ", user_id=" + user_id + ", user_email=" + user_email + ", start_datetime=" + start_datetime
				+ ", end_datetime=" + end_datetime + ", tota_calories_burnt=" + tota_calories_burnt + "]";
	}

 

   
}