package com.qdazzle.pushPlugin;

import java.util.Set;
import java.util.TreeSet;

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
	//单例
	public static AlarmPushPlugin getInstance() {
		if(alarmPushPlugin == null)
			alarmPushPlugin = new AlarmPushPlugin();
		return alarmPushPlugin;
	}
	private static Context mContext=null;
	private static PendingIntent alarmIntent=null;
//	public static Set<QdNotification> currentNotification = new TreeSet<QdNotification>();
	public static Set<Long>currentNotification=new TreeSet<Long>();
	
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
	
	public void addNotification(long minute)
	{
		//假如同一时间的推送已经被设置的话直接返回
		if(checkNotificationIsSet(minute))
		{
			return;
		}
		Log.e(TAG,"currentNotification.add"+minute);
		currentNotification.add(minute);
	}
	public boolean checkNotificationIsSet(long minute)
	{
		for(Long notification:currentNotification) {
			if(notification==minute)
			{
				return true;
			}
		}
		return false;
	}
	public void deleteNotification(long minute)
	{
		for(Long notification:currentNotification) {
			if(notification==minute)
			{
				currentNotification.remove(notification);
				break;
			}
		}
	}
}
