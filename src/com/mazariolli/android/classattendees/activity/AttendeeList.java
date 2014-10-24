package com.mazariolli.android.classattendees.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.mazariolli.android.classattendees.R;
import com.mazariolli.android.classattendees.adapter.AttendeeListItemAdapter;
import com.mazariolli.android.classattendees.dao.Attendee;
import com.mazariolli.android.classattendees.dao.AttendeeDAO;
import com.mazariolli.android.classattendees.extra.Extras;
import com.mazariolli.android.classattendees.task.ScoreSyncTask;

public class AttendeeList extends Activity {
	
	private AttendeeDAO dao;
	private ListView attendeesListView;
	private List<Attendee> attendees;
	
	private void loadAttendees() {
		attendees = dao.findAll();
        AttendeeListItemAdapter attendeesAdapter = new AttendeeListItemAdapter(this, attendees);
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
			public void onItemClick(AdapterView<?> adapter, View view, int pos, long id) {
				Intent i = new Intent(AttendeeList.this, AttendeeCreate.class);
				Attendee a = (Attendee) adapter.getItemAtPosition(pos);
				i.putExtra(Extras.SELECTED_ATTENDEE, a);
				startActivity(i);
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
    	final Attendee selectedAttendee = attendees.get(adapter.position);
    	
    	MenuItem callItem = menu.add("Ligar");
    	Intent callIntent = new Intent(Intent.ACTION_CALL);
    	callIntent.setData(Uri.parse("tel:" + selectedAttendee.getPhone()));
    	callItem.setIntent(callIntent);
    	
    	MenuItem textItem = menu.add("Enviar SMS");
    	Intent textIntent = new Intent(Intent.ACTION_VIEW);
    	textIntent.putExtra("sms_body", "Oi");
    	textIntent.setData(Uri.parse("sms:" + selectedAttendee.getPhone()));
    	textItem.setIntent(textIntent);
    	
    	MenuItem mapItem = menu.add("Mostrar no mapa");
    	Intent mapIntent = new Intent(Intent.ACTION_VIEW);
    	mapIntent.setData(Uri.parse("geo:0,0?z=14&q=" + selectedAttendee.getAddress()));
    	mapItem.setIntent(mapIntent);
    	
    	MenuItem websiteItem = menu.add("Abrir website");
    	Intent websiteIntent = new Intent(Intent.ACTION_VIEW);
    	websiteIntent.setData(Uri.parse("http:" + selectedAttendee.getWebsite()));
    	websiteItem.setIntent(websiteIntent);
    	
    	MenuItem removeItem = menu.add("Remover");    	
    	removeItem.setOnMenuItemClickListener(new OnMenuItemClickListener() {
			
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				new AlertDialog.Builder(AttendeeList.this)
				.setIcon(android.R.drawable.ic_dialog_alert)
				.setTitle("Remover")
				.setMessage("Confirma a remoção?")
				.setPositiveButton("Remover", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dao.delete(selectedAttendee);
						loadAttendees();
					}
					
				})
				.setNegativeButton("Cancelar", null)
				.show();
				return true;
			}
		});

    	MenuItem emailItem = menu.add("Enviar e-mail");
    	Intent emailIntent = new Intent(Intent.ACTION_SEND);
    	emailIntent.setType("message/rfc822");
    	emailIntent.putExtra(Intent.EXTRA_EMAIL, "caelum@caelum.com.br");
    	emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Assunto");
    	emailIntent.putExtra(Intent.EXTRA_TEXT, "Texto");
    	emailItem.setIntent(emailIntent);
    }
    
    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
    	switch(item.getItemId()) {
    	case R.id.menu_main__create:
    		startActivity(new Intent(this, AttendeeCreate.class));
    		break;
    	case R.id.menu_main__sync:
    		new ScoreSyncTask(this, attendees).execute();
    		break;
    	case R.id.menu_main__tests:
    		startActivity(new Intent(this, TestActivity.class));
    		break;
    	case R.id.menu_main__map:
    		startActivity(new Intent(this, MapActivity.class));
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
