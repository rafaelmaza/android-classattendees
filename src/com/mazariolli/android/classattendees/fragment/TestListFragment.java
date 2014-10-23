package com.mazariolli.android.classattendees.fragment;

import java.util.Arrays;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.mazariolli.android.classattendees.R;
import com.mazariolli.android.classattendees.dao.Test;

public class TestListFragment extends Fragment {
	
	private ListView testsListView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View layout = inflater.inflate(R.layout.fragment_test_list, container, false);
		testsListView = (ListView) layout.findViewById(R.id.fragment_test_list__list);
		
		Test test1 = new Test("20/03/2012", "Matemática");
		test1.setTopics(Arrays.asList("Algebra linear", "Integral", "Diferencial"));
		
		Test test2 = new Test("25/03/2012", "Português");
		test2.setTopics(Arrays.asList("Complemento nominal", "Orações subordinadas"));
		
		List<Test> tests = Arrays.asList(test1, test2);
		
		testsListView.setAdapter(new ArrayAdapter<Test>(getActivity(), android.R.layout.simple_list_item_1, tests));
		testsListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapter, View view, int pos,
					long id) {
				Test selectedTest = (Test) adapter.getItemAtPosition(pos);
				Toast.makeText(getActivity(), "Prova selecionada: " + selectedTest.getSubject(), Toast.LENGTH_LONG).show();
			}
		});
		
		return layout;
	}
	
}
