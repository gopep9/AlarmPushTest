package com.example.pushtest;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

import com.qdazzle.pushPlugin.AlarmPushPlugin;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.text.Editable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener{

	private Button btnStart;
	private Button btnStop;
	private Button btnDelay;
	private Button btnStartService;
	private Button btnStopServer;
	private TextView messageText;
	
	private EditText triggerTimeEdit;
	private EditText NotificationIdEdit;
	private Button btnAddPush;
	private Button btnDelAllPush;
	private EditText periodEdit;
	
	final static String TAG=MainActivity.class.getName();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(ResUtil.getLayoutId(this, "activity_main"));
		initView();
	}
	private void initView() {
		btnStart=(Button) findViewById(ResUtil.getId(this, "btnStart"));
		btnStart.setOnClickListener(this);
		btnDelay=(Button) findViewById(ResUtil.getId(this, "btnDelay"));
		btnDelay.setOnClickListener(this);
		btnStartService=(Button) findViewById(ResUtil.getId(this, "btnStartServer"));
		btnStartService.setOnClickListener(this);
		btnStopServer=(Button) findViewById(ResUtil.getId(this, "btnStopServer"));
		btnStopServer.setOnClickListener(this);
		messageText=(TextView)findViewById(ResUtil.getId(this, "messageText"));
		
		triggerTimeEdit=(EditText)findViewById(ResUtil.getId(this, "triggerTimeEdit"));
		NotificationIdEdit=(EditText)findViewById(ResUtil.getId(this, "NotificationIdEdit"));
		btnAddPush=(Button)findViewById(ResUtil.getId(this, "btnAddPush"));
		btnDelAllPush=(Button)findViewById(ResUtil.getId(this, "btnDelAllPush"));
		btnAddPush.setOnClickListener(this);
		btnDelAllPush.setOnClickListener(this);
		periodEdit=(EditText)findViewById(ResUtil.getId(this, "periodEdit"));
	}
		
	@Override
	public void onClick(View v) {
		int id=v.getId();
		if(id==ResUtil.getId(this, "btnStart"))
		{
			AlarmPushPlugin.getInstance().init(this, "http://172.30.50.1/AndroidPush/pushMessage.php", 80, "90155", "10052", "1", "com.example.pushtest", 1);
		}
		else if(id==ResUtil.getId(this, "btnDelay"))
		{
			Log.i(TAG,"the delay button is trigger");
			AlarmPushPlugin.getInstance().cancel();
		}
		//开启服务
		else if(id==ResUtil.getId(this, "btnStartServer"))
		{
			
		}
		else if(id==ResUtil.getId(this, "btnStopServer"))
		{
		}
		else if(id==ResUtil.getId(this, "btnAddPush"))
		{
		}
		else if(id==ResUtil.getId(this, "btnDelAllPush"))
		{
		}
	}
	private int noticeCount=0;//用于区分不同的PendingIntent，在新生成一个PendingIntent以后后加1
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		
	}
}
