package com.mazariolli.android.classattendees.helper;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;

import com.mazariolli.android.classattendees.R;
import com.mazariolli.android.classattendees.dao.Attendee;

public class AttendeeCreateHelper {
	private Attendee attendee;
	
	private EditText name;
	private EditText phone;
	private EditText website;
	private EditText address;
	private RatingBar score;
	private ImageView photo;
	
	public AttendeeCreateHelper(Activity activity) {
		attendee = new Attendee();
		
		name = (EditText) activity.findViewById(R.id.activity_attendee_create__name);
		phone = (EditText) activity.findViewById(R.id.activity_attendee_create__phone);
		website = (EditText) activity.findViewById(R.id.activity_attendee_create__website);
		address = (EditText) activity.findViewById(R.id.activity_attendee_create__address);
		score = (RatingBar) activity.findViewById(R.id.activity_attendee_create__score);
		photo = (ImageView) activity.findViewById(R.id.activity_attendee_create__photo);
	}
	
	public Attendee getAttendee() {
		attendee.setName(name.getText().toString());
		attendee.setPhone(phone.getText().toString());
		attendee.setWebsite(website.getText().toString());
		attendee.setAddress(address.getText().toString());
		attendee.setScore(Double.valueOf(score.getProgress()));
		return attendee;
	}

	public void setAttendee(Attendee attendee) {
		this.attendee = attendee;
		
		name.setText(attendee.getName());
		phone.setText(attendee.getPhone());
		website.setText(attendee.getWebsite());
		address.setText(attendee.getAddress());
		score.setProgress(attendee.getScore().intValue());
		if(attendee.getPhoto() != null) {
			loadImage(attendee.getPhoto());
		}
	}
	
	public ImageView getPhoto() {
		return photo;
	}

	public void loadImage(String photoPath) {
		Bitmap photoBitmap = BitmapFactory.decodeFile(photoPath);
		Bitmap scaledPhotoBitmap = Bitmap.createScaledBitmap(photoBitmap, 500, 500, true);
		
		attendee.setPhoto(photoPath);
		photo.setImageBitmap(scaledPhotoBitmap);
	}
}
