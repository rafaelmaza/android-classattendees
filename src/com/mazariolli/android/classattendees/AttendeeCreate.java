package com.mazariolli.android.classattendees;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class AttendeeCreate extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_attendee_create);
		
		Button save = (Button) findViewById(R.id.activity_attendee_create__submit);
		save.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(AttendeeCreate.this, "Clicou Salvar", Toast.LENGTH_SHORT).show();
				finish();
			}
		});
	}

}