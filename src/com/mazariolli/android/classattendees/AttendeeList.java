package com.mazariolli.android.classattendees;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class AttendeeList extends Activity {
	
	private AttendeeDAO dao;
	private ListView attendeesListView;
	private List<Attendee> attendees;
	
	private void loadAttendees() {
		attendees = dao.findAll();
        ArrayAdapter<Attendee> attendeesAdapter = new ArrayAdapter<Attendee>(this, android.R.layout.simple_list_item_1, attendees);
        attendeesListView.setAdapter(attendeesAdapter);
	}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendee_list);
        
        // initialize variables
        dao = new AttendeeDAO(this);
        attendeesListView = (ListView) findViewById(R.id.activity_attendees_list__attendee_list);
        attendees = new ArrayList<Attendee>();
        
        // set listeners
        attendeesListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapter, View view, int pos,
					long id) {
				Toast.makeText(AttendeeList.this, "Posição: " + pos, Toast.LENGTH_LONG).show();
				
			}
        	
        });
        
        registerForContextMenu(attendeesListView);
        
//        attendeesListView.setOnItemLongClickListener(new OnItemLongClickListener() {
//
//			@Override
//			public boolean onItemLongClick(AdapterView<?> adapter, View view, int pos,
//					long id) {
//				String attendee = (String) adapter.getItemAtPosition(pos);
//				Toast.makeText(AttendeeList.this, attendee, Toast.LENGTH_LONG).show();
//				return true;
//			}
//        	
//        });
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	getMenuInflater().inflate(R.menu.main, menu);
    	return true;
    }
    
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
    		ContextMenuInfo menuInfo) {
    	super.onCreateContextMenu(menu, v, menuInfo);
    	final AdapterContextMenuInfo adapter = (AdapterContextMenuInfo) menuInfo;
    	
    	MenuItem removeItem = menu.add("Remove");
    	removeItem.setOnMenuItemClickListener(new OnMenuItemClickListener() {
			
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				dao.delete(attendees.get(adapter.position));
				loadAttendees();
				return true;
			}
		});
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
    
    @Override
    protected void onResume() {
    	super.onResume();
    	loadAttendees();
    }
}
