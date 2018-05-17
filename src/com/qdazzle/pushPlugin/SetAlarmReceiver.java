package com.qdazzle.pushPlugin;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
//使用闹钟实现向服务器请求推送内容和设置推送内容
public class SetAlarmReceiver extends BroadcastReceiver{

	private final String TAG=SetAlarmReceiver.class.getName();
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		Log.i(TAG,"onReceiver and intent is "+intent.toString());
	}

}
