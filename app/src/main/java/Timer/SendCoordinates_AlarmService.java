package Timer;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.widget.Toast;

import com.backgroundservice.MainActivity;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Shahbaz on 12/16/2017.
 */

public class SendCoordinates_AlarmService extends Service {

	private Timer timer = new Timer();
	private final Handler myHandler = new Handler();


	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {

		doSomethingRepeatedly();
		return START_STICKY;// We want this service to continue running until it is explicitly stopped, so return sticky.
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Toast.makeText(this, "MyService Completed or Stopped.", Toast.LENGTH_SHORT).show();
		timer.cancel();
	}

	private void doSomethingRepeatedly() {

		try{
			System.out.println("UPDATE_INTERVAL is :"+ MainActivity.interval);
			timer.scheduleAtFixedRate(new TimerTask() {

				@Override
				public void run() {
					myHandler.post(updateRunnable);

				}

			}, 0, MainActivity.interval);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	final Runnable updateRunnable = new Runnable() {
		public void run() {
			// call the activity method that updates the UI

			Toast.makeText(SendCoordinates_AlarmService.this,"Service running ",Toast.LENGTH_SHORT).show();


		}
	};
}
