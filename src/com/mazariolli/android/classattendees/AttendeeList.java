package com.mazariolli.android.classattendees;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class AttendeeList extends Activity {
	
	private String[] names = {"Rafael","Alessandra","Rosana"};
	private ListView attendeesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendee_list);
        
        attendeesList = (ListView) findViewById(R.id.activity_attendees_list__attendee_list);
        ArrayAdapter<String> attendeesAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, names);
        attendeesList.setAdapter(attendeesAdapter);
        attendeesList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapter, View view, int pos,
					long id) {
				Toast.makeText(AttendeeList.this, "Posição: " + pos, Toast.LENGTH_LONG).show();
				
			}
        	
        });
        
        attendeesList.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> adapter, View view, int pos,
					long id) {
				String attendee = (String) adapter.getItemAtPosition(pos);
				Toast.makeText(AttendeeList.this, attendee, Toast.LENGTH_LONG).show();
				return true;
			}
        	
        });
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	getMenuInflater().inflate(R.menu.main, menu);
    	return true;
    }
    
    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
    	switch(item.getItemId()) {
    	case R.id.menu_main__create:
    		startActivity(new Intent(this, AttendeeCreate.class));
    		break;
    	}
    	return super.onMenuItemSelected(featureId, item);
    }
}
