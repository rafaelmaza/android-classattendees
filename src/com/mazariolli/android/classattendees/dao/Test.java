package com.mazariolli.android.classattendees.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class Test implements Serializable {
	
	private String date;
	private String subject;
	private String description;
	private List<String> topics = new ArrayList<String>();
	
	public Test(String date, String subject) {
		this.date = date;
		this.subject = subject;
	}
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<String> getTopics() {
		return topics;
	}
	public void setTopics(List<String> topics) {
		this.topics = topics;
	}
	
	@Override
	public String toString() {
		return this.subject;
	}

}
