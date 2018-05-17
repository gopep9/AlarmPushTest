/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: C:\\Users\\A1-0430\\eclipse-workspace\\AlarmPushTest\\src\\com\\qdazzle\\pushPlugin\\aidl\\INotificationService.aidl
 */
package com.qdazzle.pushPlugin.aidl;
public interface INotificationService extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements com.qdazzle.pushPlugin.aidl.INotificationService
{
private static final java.lang.String DESCRIPTOR = "com.qdazzle.pushPlugin.aidl.INotificationService";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an com.qdazzle.pushPlugin.aidl.INotificationService interface,
 * generating a proxy if needed.
 */
public static com.qdazzle.pushPlugin.aidl.INotificationService asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof com.qdazzle.pushPlugin.aidl.INotificationService))) {
return ((com.qdazzle.pushPlugin.aidl.INotificationService)iin);
}
return new com.qdazzle.pushPlugin.aidl.INotificationService.Stub.Proxy(obj);
}
@Override public android.os.IBinder asBinder()
{
return this;
}
@Override public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException
{
switch (code)
{
case INTERFACE_TRANSACTION:
{
reply.writeString(DESCRIPTOR);
return true;
}
case TRANSACTION_scheduleNotification:
{
data.enforceInterface(DESCRIPTOR);
int _arg0;
_arg0 = data.readInt();
int _arg1;
_arg1 = data.readInt();
java.lang.String _arg2;
_arg2 = data.readString();
java.lang.String _arg3;
_arg3 = data.readString();
java.lang.String _arg4;
_arg4 = data.readString();
int _arg5;
_arg5 = data.readInt();
boolean _result = this.scheduleNotification(_arg0, _arg1, _arg2, _arg3, _arg4, _arg5);
reply.writeNoException();
reply.writeInt(((_result)?(1):(0)));
return true;
}
case TRANSACTION_unscheduleNotification:
{
data.enforceInterface(DESCRIPTOR);
int _arg0;
_arg0 = data.readInt();
boolean _result = this.unscheduleNotification(_arg0);
reply.writeNoException();
reply.writeInt(((_result)?(1):(0)));
return true;
}
case TRANSACTION_unscheduleAllNotifications:
{
data.enforceInterface(DESCRIPTOR);
boolean _result = this.unscheduleAllNotifications();
reply.writeNoException();
reply.writeInt(((_result)?(1):(0)));
return true;
}
case TRANSACTION_setForgroundProcName:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
boolean _result = this.setForgroundProcName(_arg0);
reply.writeNoException();
reply.writeInt(((_result)?(1):(0)));
return true;
}
case TRANSACTION_stopNotificationService:
{
data.enforceInterface(DESCRIPTOR);
this.stopNotificationService();
reply.writeNoException();
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements com.qdazzle.pushPlugin.aidl.INotificationService
{
private android.os.IBinder mRemote;
Proxy(android.os.IBinder remote)
{
mRemote = remote;
}
@Override public android.os.IBinder asBinder()
{
return mRemote;
}
public java.lang.String getInterfaceDescriptor()
{
return DESCRIPTOR;
}
@Override public boolean scheduleNotification(int id, int triggerMinutes, java.lang.String title, java.lang.String content, java.lang.String tickerText, int periodMinutes) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
boolean _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(id);
_data.writeInt(triggerMinutes);
_data.writeString(title);
_data.writeString(content);
_data.writeString(tickerText);
_data.writeInt(periodMinutes);
mRemote.transact(Stub.TRANSACTION_scheduleNotification, _data, _reply, 0);
_reply.readException();
_result = (0!=_reply.readInt());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public boolean unscheduleNotification(int id) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
boolean _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(id);
mRemote.transact(Stub.TRANSACTION_unscheduleNotification, _data, _reply, 0);
_reply.readException();
_result = (0!=_reply.readInt());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public boolean unscheduleAllNotifications() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
boolean _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_unscheduleAllNotifications, _data, _reply, 0);
_reply.readException();
_result = (0!=_reply.readInt());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public boolean setForgroundProcName(java.lang.String procName) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
boolean _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(procName);
mRemote.transact(Stub.TRANSACTION_setForgroundProcName, _data, _reply, 0);
_reply.readException();
_result = (0!=_reply.readInt());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public void stopNotificationService() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_stopNotificationService, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
}
static final int TRANSACTION_scheduleNotification = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_unscheduleNotification = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
static final int TRANSACTION_unscheduleAllNotifications = (android.os.IBinder.FIRST_CALL_TRANSACTION + 2);
static final int TRANSACTION_setForgroundProcName = (android.os.IBinder.FIRST_CALL_TRANSACTION + 3);
static final int TRANSACTION_stopNotificationService = (android.os.IBinder.FIRST_CALL_TRANSACTION + 4);
}
public boolean scheduleNotification(int id, int triggerMinutes, java.lang.String title, java.lang.String content, java.lang.String tickerText, int periodMinutes) throws android.os.RemoteException;
public boolean unscheduleNotification(int id) throws android.os.RemoteException;
public boolean unscheduleAllNotifications() throws android.os.RemoteException;
public boolean setForgroundProcName(java.lang.String procName) throws android.os.RemoteException;
public void stopNotificationService() throws android.os.RemoteException;
}
