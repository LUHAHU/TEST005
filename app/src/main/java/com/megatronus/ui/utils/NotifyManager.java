package com.megatronus.ui.utils;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.drawable.Icon;
import com.megatronus.ui.R;
import android.app.PendingIntent;
import android.content.Intent;
import com.megatronus.ui.EditBillActivity;

public class NotifyManager
{
	
	public NotifyManager(){
		
	}
	
	public void CreateNotify(Context context,String title,String msg){
		NotificationManager nm = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);

		Notification.Builder builder = new Notification.Builder(context);
		builder.setContentText(msg);
		builder.setContentTitle(title);
		builder.setSmallIcon(Icon.createWithResource(context,R.drawable.icon));
		builder.setAutoCancel(true);
		builder.setContentIntent(PendingIntent.getActivity(context,0,new Intent(context,EditBillActivity.class),PendingIntent.FLAG_UPDATE_CURRENT));

		nm.notify(56,builder.build());
	}
	
	public void CreateNotify(Context context,String msg){
		CreateNotify(context,"005AL",msg);
	}
}
