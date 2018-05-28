package com.qdazzle.pushPlugin;

import com.example.pushtest.MainActivity;
import com.example.pushtest.R;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.IBinder;

public class UselessService2 extends Service {

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int onStartCommand(Intent intent,int flags,int startId) {
		Notification.Builder Builder = new Notification.Builder(this.getApplicationContext());
		Intent nfIntent=new Intent(this,MainActivity.class);
		Builder.setContentIntent(PendingIntent.getActivity(this, 0, nfIntent, 0))
		.setLargeIcon(BitmapFactory.decodeResource(this.getResources(), R.drawable.ic_launcher))
		.setContentTitle("下拉列表中的Title2")
		.setSmallIcon(R.drawable.ic_launcher)
		.setContentText("要显示的内容2")
		.setWhen(System.currentTimeMillis());
		Notification notification=Builder.build();
		notification.defaults=Notification.DEFAULT_SOUND;
		startForeground(546, notification);
		stopForeground(true);
		stopSelf();
		return START_NOT_STICKY;
	}
}
