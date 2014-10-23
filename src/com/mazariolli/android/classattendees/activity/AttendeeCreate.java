package com.mazariolli.android.classattendees.activity;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

import com.mazariolli.android.classattendees.R;
import com.mazariolli.android.classattendees.dao.Attendee;
import com.mazariolli.android.classattendees.dao.AttendeeDAO;
import com.mazariolli.android.classattendees.extra.Extras;
import com.mazariolli.android.classattendees.helper.AttendeeCreateHelper;

public class AttendeeCreate extends Activity {

	private AttendeeCreateHelper helper;
	private String photoPath;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
		setContentView(R.layout.activity_attendee_create);
		helper = new AttendeeCreateHelper(this);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		Intent i = getIntent();
		if(i.hasExtra(Extras.SELECTED_ATTENDEE)) {
			helper.setAttendee((Attendee) i.getSerializableExtra(Extras.SELECTED_ATTENDEE));
		}
		
		ImageView photo = helper.getPhoto();
		photo.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				photoPath = getExternalFilesDir(null) + "/" + System.currentTimeMillis() + ".jpg";
				File file = new File(photoPath);
				Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				i.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
				startActivityForResult(i, 123);
			}
		});
		
		Button save = (Button) findViewById(R.id.activity_attendee_create__submit);
		save.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Attendee attendee = helper.getAttendee();
				AttendeeDAO dao = new AttendeeDAO(AttendeeCreate.this);
				if(attendee.getId() != null) {
					dao.update(attendee);
				} else {
					dao.insert(attendee);
				}
				finish();
			}
		});
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(requestCode == 123) {
			if(resultCode == RESULT_OK) {
				helper.loadImage(photoPath);
			} else {
				photoPath = null;
			}
		}
	}

}
