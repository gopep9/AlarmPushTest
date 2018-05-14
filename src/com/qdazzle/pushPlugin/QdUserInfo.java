package com.qdazzle.pushPlugin;

import java.io.Serializable;
//存储用户信息，可以用writeObject把信息存到本地
public class QdUserInfo implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4296906223898600143L;
	
//	private int sid = 0;
//	private int pid = 0;
//	private int rid = 0;
//	private String deviceId = "";
	//区分不同的专服
	private String platformId="";
	//区分不同的渠道
	private String channelId="";
	//区分不同的包
	private String pushPackId="";
	private String pushUrl = "";
	private int pushPort = 0;
	
	public String getPlatformId()
	{
		return platformId;
	}
	
	public void setPlatformId(String id)
	{
		platformId=id;
	}
	
	public String getChannelId()
	{
		return channelId;
	}
	
	public void setChannelId(String id)
	{
		channelId=id;
	}
	
	public String getPushPackId()
	{
		return pushPackId;
	}
	
	public void setPushPackId(String id)
	{
		pushPackId=id;
	}
	
	public String getPushUrl()
	{
		return pushUrl;
	}
	public void setPushUrl(String pushUrl)
	{
		this.pushUrl = pushUrl;
	}
	public int getPushPort()
	{
		return pushPort;
	}
	public void setPushPort(int pushPort)
	{
		this.pushPort = pushPort;
	}

}
