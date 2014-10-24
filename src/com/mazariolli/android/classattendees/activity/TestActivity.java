package com.mazariolli.android.classattendees.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

import com.mazariolli.android.classattendees.R;
import com.mazariolli.android.classattendees.dao.Test;
import com.mazariolli.android.classattendees.extra.Extras;
import com.mazariolli.android.classattendees.fragment.TestViewFragment;

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
	
	private boolean isTablet() {
		return getResources().getBoolean(R.bool.isTablet);
	}

	public void showTest(Test selectedTest) {
		Bundle extras = new Bundle();
		extras.putSerializable(Extras.SELECTED_TEST, selectedTest);
		if(isTablet()) {
			TestViewFragment testView = (TestViewFragment) getSupportFragmentManager().findFragmentById(R.id.activity_test__view);
			testView.setArgumentsToViews(extras);
		} else {
			TestViewFragment testView = new TestViewFragment();
			testView.setArguments(extras);
			FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
			transaction.replace(R.id.activity_test__list, testView);
			transaction.addToBackStack(null);
			transaction.commit();
		}
	}
	
}
