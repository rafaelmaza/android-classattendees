package com.mazariolli.android.classattendees.adapter;

import java.util.List;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mazariolli.android.classattendees.R;
import com.mazariolli.android.classattendees.dao.Attendee;

public class AttendeeListItemAdapter extends BaseAdapter {
	
	private List<Attendee> attendees;
	private Activity activity;
	
	public AttendeeListItemAdapter(Activity activity, List<Attendee> attendees) {
		this.activity = activity;
		this.attendees = attendees;
	}

	@Override
	public int getCount() {
		return attendees.size();
	}

	@Override
	public Object getItem(int pos) {
		return attendees.get(pos);
	}

	@Override
	public long getItemId(int pos) {
		return attendees.get(pos).getId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup container) {
		View layout = activity.getLayoutInflater().inflate(R.layout.adapter_attendee_list_item, container, false);
		Attendee a = attendees.get(position);
		
		TextView name = (TextView) layout.findViewById(R.id.adapter_attendee_list_item__name);
		name.setText(a.getName());
		
		ImageView photo = (ImageView) layout.findViewById(R.id.adapter_attendee_list_item__photo);
		Bitmap bm;
		if(a.getPhoto() != null) {
			bm = BitmapFactory.decodeFile(a.getPhoto());
		} else {
			bm = BitmapFactory.decodeResource(activity.getResources(), R.drawable.ic_no_image);
		}
		bm = Bitmap.createScaledBitmap(bm, 50, 50, true);
		photo.setImageBitmap(bm);
		
		return layout;
	}

}
