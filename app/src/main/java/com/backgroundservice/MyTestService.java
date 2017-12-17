package com.backgroundservice;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import java.util.Calendar;

/**
 * Created by Shahbaz on 12/16/2017.
 */

public class MyTestService extends IntentService {
	public MyTestService() {
		super("com.backgroundservice.MyTestService");
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		// Do the task here
		System.out.println(Calendar.getInstance().getTimeInMillis()+"");
	}
}