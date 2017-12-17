package com.backgroundservice;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import Timer.SendCoordinates_AlarmService;

/**
 * Created by Shahbaz on 12/16/2017.
 */

public class MainActivity extends Activity {

//	private static final int interval = 1 * 60 * 1000; //i minute
	public static final int interval = 15 * 1000; //15 secs

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//scheduleAlarm();
		Button btn_start_service = (Button) findViewById(R.id.btn_start_service);
		btn_start_service.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startService(new Intent(getBaseContext(), SendCoordinates_AlarmService.class));
			}
		});

		Button btn_stop_service = (Button) findViewById(R.id.btn_stop_service);
		btn_stop_service.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				stopService(new Intent(getBaseContext(), SendCoordinates_AlarmService.class));
			}
		});


	}

	// Setup a recurring alarm every half hour
	public void scheduleAlarm() {
		// Construct an intent that will execute the AlarmReceiver
		Intent intent = new Intent(getApplicationContext(), MyAlarmReceiver.class);
		// Create a PendingIntent to be triggered when the alarm goes off
		final PendingIntent pIntent = PendingIntent.getBroadcast(this, MyAlarmReceiver.REQUEST_CODE,intent, PendingIntent.FLAG_UPDATE_CURRENT);
		// Setup periodic alarm every every half hour from this point onwards
		long firstMillis = System.currentTimeMillis(); // alarm is set right away
		AlarmManager alarm = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
		// First parameter is the type: ELAPSED_REALTIME, ELAPSED_REALTIME_WAKEUP, RTC_WAKEUP
		// Interval can be INTERVAL_FIFTEEN_MINUTES, INTERVAL_HALF_HOUR, INTERVAL_HOUR, INTERVAL_DAY
		alarm.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, firstMillis,MainActivity.interval, pIntent);
	}

	public void cancelAlarm() {
		Intent intent = new Intent(getApplicationContext(), MyAlarmReceiver.class);
		final PendingIntent pIntent = PendingIntent.getBroadcast(this, MyAlarmReceiver.REQUEST_CODE,intent, PendingIntent.FLAG_UPDATE_CURRENT);
		AlarmManager alarm = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
		alarm.cancel(pIntent);
	}
}
