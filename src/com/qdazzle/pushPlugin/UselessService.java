package com.qdazzle.pushPlugin;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.example.pushtest.MainActivity;
import com.example.pushtest.R;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
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
		Log.i(TAG, "onStartCommand()");
		Notification.Builder Builder = new Notification.Builder(this.getApplicationContext());
		Intent nfIntent=new Intent(this,MainActivity.class);
		Builder.setContentIntent(PendingIntent.getActivity(this, 0, nfIntent, 0))
		.setLargeIcon(BitmapFactory.decodeResource(this.getResources(), R.drawable.ic_launcher))
		.setContentTitle("下拉列表中的Title")
		.setSmallIcon(R.drawable.ic_launcher)
		.setContentText("要显示的内容")
		.setWhen(System.currentTimeMillis());
		Notification notification=Builder.build();
		notification.defaults=Notification.DEFAULT_SOUND;
		startForeground(546, notification);
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
					Log.i(TAG," is running");
					try {
						//睡眠10秒
						Thread.sleep(10000);
						//尝试添加请求服务器的代码，看下多久会被干
						String receiveStr = getServerPushMessage(10, false, "http://172.30.50.1", 80);
						Log.i(TAG,"receive String:"+receiveStr);
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
	
	private String getServerPushMessage(int secsToWait,boolean hasForground,String urlStr,int port)
	{
		try {
			int timeLeftMiliSec=secsToWait*1000;
			StringBuilder response=new StringBuilder();
			BufferedReader reader=null;
			URL url=new URL(urlStr);
			HttpURLConnection connection=(HttpURLConnection)url.openConnection();
			connection.setRequestMethod("GET");
			connection.setConnectTimeout(timeLeftMiliSec);
			connection.setReadTimeout(timeLeftMiliSec);
			InputStream inputStream=connection.getInputStream();
			reader=new BufferedReader(new InputStreamReader(inputStream));
			String line;
			while((line=reader.readLine())!=null) {
				response.append(line);
			}
			Log.i(TAG,"request:"+urlStr+" port:"+port+" and receive string:"+response.toString());
			return response.toString();
		}catch (Exception e) {
			// TODO: handle exception
			Log.e(TAG,"connect error:"+e.toString());
		}
		return "";
	}

}
