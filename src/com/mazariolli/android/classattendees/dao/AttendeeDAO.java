package com.mazariolli.android.classattendees.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class AttendeeDAO extends SQLiteOpenHelper {
	private static final int VERSION = 1;
	private static final String TABLE = "Attendees";
	private static final String DATABASE = "ClassAttendees";

	public AttendeeDAO(Context context) {
		super(context, DATABASE, null, VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE " + TABLE + " (id INTEGER PRIMARY KEY, " +
				"name TEXT UNIQUE NOT NULL, phone TEXT, " +
				"address TEXT, website TEXT, score REAL, photo TEXT)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int previousVersion, int currentVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE);
		onCreate(db);
	}

	public void insert(Attendee attendee) {
		ContentValues cv = new ContentValues();
		cv.put("name", attendee.getName());
		cv.put("phone", attendee.getPhone());
		cv.put("address", attendee.getAddress());
		cv.put("website", attendee.getWebsite());
		cv.put("score", attendee.getScore());
		cv.put("photo", attendee.getPhoto());
		getWritableDatabase().insert(TABLE, null, cv);
		close();
	}
	
	public List<Attendee> findAll() {
		List<Attendee> attendees = new ArrayList<Attendee>();
		SQLiteDatabase db = getReadableDatabase();
		Cursor c = db.rawQuery("SELECT * FROM " + TABLE, null);
		while(c.moveToNext()) {
			Attendee a = new Attendee();
			a.setId(c.getLong(c.getColumnIndex("id")));
			a.setName(c.getString(c.getColumnIndex("name")));
			a.setPhone(c.getString(c.getColumnIndex("phone")));
			a.setAddress(c.getString(c.getColumnIndex("address")));
			a.setWebsite(c.getString(c.getColumnIndex("website")));
			a.setScore(c.getDouble(c.getColumnIndex("score")));
			a.setPhoto(c.getString(c.getColumnIndex("photo")));
			attendees.add(a);
		}
		c.close();
		close();
		return attendees;
	}

	public void delete(Attendee attendee) {
		getWritableDatabase().delete(TABLE, "id = ?", new String[] { attendee.getId().toString() });
		close();
	}

	public void update(Attendee attendee) {
		ContentValues cv = new ContentValues();
		cv.put("name", attendee.getName());
		cv.put("phone", attendee.getPhone());
		cv.put("address", attendee.getAddress());
		cv.put("website", attendee.getWebsite());
		cv.put("score", attendee.getScore());
		cv.put("photo", attendee.getPhoto());
		getWritableDatabase().update(TABLE, cv, "id = ?", new String[] { attendee.getId().toString() });
		close();
	}

	public boolean findAttendeeByPhone(String phone) {
		SQLiteDatabase db = getReadableDatabase();
		Cursor c = db.query(TABLE, null, "phone = ?", new String[] { phone }, null, null, null);
		boolean foundAttendees = c.getCount() > 0;
		c.close();
		close();
		return foundAttendees;
	}

}
