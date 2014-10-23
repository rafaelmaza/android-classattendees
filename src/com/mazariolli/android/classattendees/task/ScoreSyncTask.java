package com.mazariolli.android.classattendees.task;

import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.widget.Toast;

import com.mazariolli.android.classattendees.dao.Attendee;
import com.mazariolli.android.classattendees.dao.AttendeeConverter;

public class ScoreSyncTask extends AsyncTask<Void, Void, String> {

	private Activity activity;
	private List<Attendee> attendees;
	private ProgressDialog dialog;
	
	public ScoreSyncTask(Activity activity, List<Attendee> attendees) {
		this.activity = activity;
		this.attendees = attendees;
	}
	
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		dialog = ProgressDialog.show(activity, "Sincronizando", "Aguarde...");
	}
	
	@Override
	protected String doInBackground(Void... params) {
		HttpPost post = new HttpPost("http://www.caelum.com.br/mobile");
		post.setHeader("Content-Type", "application/json");
		post.setHeader("Accept", "application/json");
		String responseJson = "";
		HttpClient client = new DefaultHttpClient();
		try {
			post.setEntity(new StringEntity(new AttendeeConverter().toJSON(attendees)));
			HttpResponse response = client.execute(post);
			responseJson = EntityUtils.toString(response.getEntity());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return responseJson;
	}
	
	@Override
	protected void onPostExecute(String result) {
		super.onPostExecute(result);
		dialog.dismiss();
		Toast.makeText(activity, "Média: " + result, Toast.LENGTH_LONG).show();
	}
}
