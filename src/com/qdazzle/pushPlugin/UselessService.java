package com.qdazzle.pushPlugin;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class UselessService extends Service {

	final String TAG=UselessService.class.getName();
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	boolean run=true;
	boolean isRuning=false;
	@Override
	public int onStartCommand(Intent intent, int flags, int startId)
	{
		Thread mPushServiceThread = new Thread(new Runnable()
		{
			@Override
			public void run() {
				Log.e(TAG,"start running a thread");
				if(isRuning==true) {
					return;
				}
				isRuning=true;
				while(run)
				{
					Log.i(TAG,"is running");
					try {
					Thread.sleep(10000);
					}catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
						isRuning=false;
						return;
					}
				}
				isRuning=false;
			}
		});

		mPushServiceThread.start();
		return 0;
	}
}
