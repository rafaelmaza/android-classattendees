package com.mazariolli.android.classattendees.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

import com.mazariolli.android.classattendees.R;
import com.mazariolli.android.classattendees.dao.AttendeeDAO;

public class SMSReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		Bundle extras = intent.getExtras();
		Object messages[] = (Object[]) extras.get("pdus");
		SmsMessage smsMessage[] = new SmsMessage[messages.length];
		for(int n=0; n<messages.length; n++) {
			smsMessage[n] = SmsMessage.createFromPdu((byte[]) messages[n]);
		}
		AttendeeDAO dao = new AttendeeDAO(context);
		if(dao.findAttendeeByPhone(smsMessage[0].getDisplayOriginatingAddress())) {
			Toast.makeText(context, "SMS de Aluno: " + smsMessage[0].getDisplayOriginatingAddress(), Toast.LENGTH_LONG).show();
			MediaPlayer mp = MediaPlayer.create(context, R.raw.msg);
			mp.start();
		}
	}

}
