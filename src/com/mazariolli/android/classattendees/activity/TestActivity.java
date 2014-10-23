package com.mazariolli.android.classattendees.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.mazariolli.android.classattendees.R;

public class TestActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.activity_test);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
//		FragmentManager manager = getSupportFragmentManager();
//		FragmentTransaction tx = manager.beginTransaction();
//		tx.replace(R.id.activity_test__frame, new TestListFragment());
//		tx.commit();
	}
	
}
