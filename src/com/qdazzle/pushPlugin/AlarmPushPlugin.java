package com.qdazzle.pushPlugin;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

public class AlarmPushPlugin {
	final String TAG=AlarmPushPlugin.class.getName();
	private AlarmPushPlugin() {}
	private static AlarmPushPlugin alarmPushPlugin=null;
	public static AlarmPushPlugin getInstance() {
		if(alarmPushPlugin == null)
			alarmPushPlugin = new AlarmPushPlugin();
		return alarmPushPlugin;
	}
	static Context mContext=null;
	static PendingIntent alarmIntent=null;
	public void init(Context context,String url,int port,String platformId,String channelId,
			String NotificationPackId,String packageId,int requestPeriod)
	{
		if(alarmIntent!=null)
		{
			getAlarmManager().cancel(alarmIntent);
			alarmIntent=null;
		}
		mContext=context;
		Intent intent=new Intent(context,SetAlarmReceiver.class);
		intent.putExtra("url", url);
		intent.putExtra("port", port);
		intent.putExtra("platformId", platformId);
		intent.putExtra("channelId", channelId);
		intent.putExtra("NotificationPackId", NotificationPackId);
		intent.putExtra("packageId", packageId);
		intent.putExtra("requestPeriod", requestPeriod);
		alarmIntent=PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//		AlarmManager aManager=(AlarmManager)context.getSystemService(Activity.ALARM_SERVICE);
		if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.KITKAT)
		{
			getAlarmManager().setInexactRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(),requestPeriod*1000*60, alarmIntent);
		}
		else
		{
			getAlarmManager().setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(),requestPeriod*1000*60, alarmIntent);
		}
		
		Intent serviceIntent=new Intent(mContext,UselessService.class);
		
		//连接没用的服务
		ServiceConnection connection = new ServiceConnection() {
			
			@Override
			public void onServiceDisconnected(ComponentName name) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onServiceConnected(ComponentName name, IBinder service) {
				// TODO Auto-generated method stub
				
			}
		};
		mContext.startService(serviceIntent);

		mContext.bindService(serviceIntent, connection, Context.BIND_AUTO_CREATE);
	}
	
	public void cancel()
	{
		if(alarmIntent!=null)
		{
			Log.i(TAG,"cancel alarmIntent "+alarmIntent.toString());
			getAlarmManager().cancel(alarmIntent);
			alarmIntent=null;
		}
	}
	
	private AlarmManager getAlarmManager() {
		return (AlarmManager)mContext.getSystemService(Activity.ALARM_SERVICE);
	}
}
