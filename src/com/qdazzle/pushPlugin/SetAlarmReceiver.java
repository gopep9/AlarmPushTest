package com.qdazzle.pushPlugin;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
//使用闹钟实现向服务器请求推送内容和设置推送内容
public class SetAlarmReceiver extends BroadcastReceiver{

//	static int lastNotificationId=0;
	private final String TAG=SetAlarmReceiver.class.getName();
	static private int NotificationId=143825;//随便起的，希望不会和其他sdk的id有冲突
	@Override
	public void onReceive(final Context context,final Intent intent) {
		
		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				Log.i(TAG,"onReceiver and intent is "+intent.toString());
				Log.i(TAG,"current minute "+currentMinute());
				String url=intent.getStringExtra("url");
				int port=intent.getIntExtra("port", 80);
				String platformId=intent.getStringExtra("platformId");
				String channelId=intent.getStringExtra("channelId");
				String NotificationPackId=intent.getStringExtra("NotificationPackId");
				String packageId=intent.getStringExtra("packageId");
				
				String requestStr=url+"?platformId="+platformId+"&channelId="+channelId;
				String responseStr=getServerPushMessage(10, requestStr, port);
				if(responseStr==null||responseStr=="")
				{
					return;
				}
				JSONObject jObject;
				int code=0;
				String tickerText="";
				String title="";
				String content="";
				long triggeringTime=0;
				try {
					jObject=new JSONObject(responseStr);
					code=jObject.getInt("code");
					if(0==code)
					{
						JSONArray jsonArrays=jObject.getJSONArray("pushMessageArray");
						for(int i=0;i<jsonArrays.length();i++)
						{	
							JSONObject jsonArray=jsonArrays.getJSONObject(i);
							tickerText=jsonArray.getString("tickerText");
							title=jsonArray.getString("title");
							content=jsonArray.getString("content");
							triggeringTime=jsonArray.getLong("triggeringTime");
//							NotificationId=jsonArray.getInt("notificationId");
							Log.e(TAG,"notification set"+AlarmPushPlugin.currentNotification);
							if(!AlarmPushPlugin.getInstance().checkNotificationIsSet(triggeringTime)&&triggeringTime>currentMinute()) {
								AlarmPushPlugin.getInstance().addNotification(triggeringTime);
								addAlarmToNotification(NotificationId, triggeringTime, title, content, tickerText,context);
								NotificationId++;
							}
						}
					}else {
						Log.i(TAG,"receivePushMessage getString:"+responseStr);
					}
				}catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}});
		thread.start();
	}
	private String getServerPushMessage(int secsToWait,String urlStr,int port)
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
	private void addAlarmToNotification(int notificationId,long triggerTimeMinute,String title,String content,String tickerText,Context context)
	{
		Log.i(TAG,"addAlarmToNotification notificationId:"+notificationId+" triggerTimeMinute:"+triggerTimeMinute+" title:"+title
				+" content:"+content);
		Intent intent=new Intent(context,AlarmReceiver.class);
		intent.putExtra("notificationId", notificationId);
		intent.putExtra("title", title);
		intent.putExtra("content", content);
		intent.putExtra("tickerText", tickerText);
		PendingIntent pendingIntent=PendingIntent.getBroadcast(context, notificationId, intent, PendingIntent.FLAG_UPDATE_CURRENT);
		AlarmManager aManager=(AlarmManager)context.getSystemService(Activity.ALARM_SERVICE);
		if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.KITKAT)
		{
			Log.i(TAG,"set alarm to push "+triggerTimeMinute);
			aManager.setExact(AlarmManager.RTC_WAKEUP, ((long)triggerTimeMinute)*1000*60, pendingIntent);
		}
		else
		{
			aManager.set(AlarmManager.RTC_WAKEUP,((long)triggerTimeMinute)*1000*60,pendingIntent);
		}
	}
	
	private long currentMinute()
	{
		return System.currentTimeMillis()/1000/60;
	}
}
