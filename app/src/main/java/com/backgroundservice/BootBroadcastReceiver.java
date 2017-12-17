package com.backgroundservice;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;

/**
 * Created by Shahbaz on 12/16/2017.
 */

public class BootBroadcastReceiver extends WakefulBroadcastReceiver {
	@Override
	public void onReceive(Context context, Intent intent) {
		// Launch the specified service when this message is received
		Intent startServiceIntent = new Intent(context, MyTestService.class);
		startWakefulService(context, startServiceIntent);
	}
}
