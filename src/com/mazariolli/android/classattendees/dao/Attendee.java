package com.mazariolli.android.classattendees.dao;

import java.io.Serializable;

import android.app.Activity;
import android.widget.EditText;
import android.widget.RatingBar;

import com.mazariolli.android.classattendees.R;

@SuppressWarnings("serial")
public class Attendee implements Serializable {

	private Long id;
	private String name;
	private String phone;
	private String website;
	private String address;
	private Double score;
	private String photo;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Double getScore() {
		return score;
	}
	public void setScore(Double score) {
		this.score = score;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
	public String toString() {
		return id + " - " + name;
	}
	
	public static Attendee createFromActivity(Activity activity) {
		Attendee attendee = new Attendee();
		attendee.setName(((EditText)activity.findViewById(R.id.activity_attendee_create__name)).getText().toString());
		attendee.setPhone(((EditText)activity.findViewById(R.id.activity_attendee_create__phone)).getText().toString());
		attendee.setWebsite(((EditText)activity.findViewById(R.id.activity_attendee_create__website)).getText().toString());
		attendee.setAddress(((EditText)activity.findViewById(R.id.activity_attendee_create__address)).getText().toString());
		attendee.setScore((double) ((RatingBar)activity.findViewById(R.id.activity_attendee_create__score)).getProgress());
		//attendee.setPhoto(((ImageView)activity.findViewById(R.id.activity_attendee_create__photo)).toString());
		return attendee;
	}
	
}
