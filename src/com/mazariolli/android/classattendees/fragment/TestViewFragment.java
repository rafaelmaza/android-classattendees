package com.mazariolli.android.classattendees.fragment;

import com.mazariolli.android.classattendees.R;
import com.mazariolli.android.classattendees.dao.Test;
import com.mazariolli.android.classattendees.extra.Extras;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class TestViewFragment extends Fragment {
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View layout = inflater.inflate(R.layout.fragment_test_view, container, false);
		return layout;
	}
	
	@Override
	public void onResume() {
		if(getArguments() != null) {
			setArgumentsToViews(getArguments());
		}
		super.onResume();
	}
	
	public void setArgumentsToViews(Bundle args) {		
		Test test = (Test) args.getSerializable(Extras.SELECTED_TEST);
		TextView subject = (TextView) getView().findViewById(R.id.fragment_test_view__subject);
		TextView date = (TextView) getView().findViewById(R.id.fragment_test_view__date);
		ListView topics = (ListView) getView().findViewById(R.id.fragment_test_view__topics);
		
		subject.setText(test.getSubject());
		date.setText(test.getDate());
		ArrayAdapter<String> topicsAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, test.getTopics());
		topics.setAdapter(topicsAdapter);
	}

}
