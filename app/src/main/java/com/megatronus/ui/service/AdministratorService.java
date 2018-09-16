package com.megatronus.ui.service;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Base64;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import com.megatronus.ui.EditBillActivity;
import com.megatronus.ui.MyApp;
import com.megatronus.ui.R;
import com.megatronus.ui.common.BaseAccessibilityService;
import com.megatronus.ui.utils.CaesarCipher;
import com.megatronus.ui.utils.CaptureMm;
import com.megatronus.ui.utils.Log;
import com.megatronus.ui.utils.NotifyManager;
import com.megatronus.weather.Conn;
import com.megatronus.weather.Weather;
import com.megatronus.weather.bean.Forecast;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AdministratorService extends BaseAccessibilityService
{
	public boolean isCapture = false;
	public boolean isOpenEdit = false ;
	public boolean isShowForecast = false; 

	private CharSequence mOldPackageName = "Initial value";
	private CaptureMm Mm = null;
	private NotifyManager nm ;

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event)
	{


        //事件类型
        int eventType = event.getEventType();

        //获取包名
        CharSequence packageName = event.getPackageName();
        if (TextUtils.isEmpty(packageName))
		{
            return;
        }

        switch (eventType)
		{

				//状态栏变化
            case AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED:
				decodeNotif(event);
                break;

				//窗口切换的时候回调
            case AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED:
            case AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED:

				ActivityChange(event);

                break;
        }


    }

	@Override
	public void onStart(Intent intent, int startId)
	{
		super.onStart(intent, startId);
	}



	@Override
	public int onStartCommand(Intent intent, int flags, int startId)
	{
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onInterrupt()
	{
		// TODO: Implement this method
	}

	@Override
	protected void onServiceConnected()
	{
		nm = new NotifyManager();
		((MyApp)getApplication()).Administrator = this ;
		((MyApp)getApplication()).isInit = true ;
		super.onServiceConnected();

		AccessibilityServiceInfo info = new AccessibilityServiceInfo();
		info.eventTypes = AccessibilityEvent.TYPES_ALL_MASK;
		info.feedbackType = AccessibilityServiceInfo.FEEDBACK_GENERIC;
		info.notificationTimeout = 100;
		//info.packageNames = new String[]{"...", "..."};
		info.flags = AccessibilityServiceInfo.FLAG_REPORT_VIEW_IDS | AccessibilityServiceInfo.FLAG_REQUEST_ENHANCED_WEB_ACCESSIBILITY;

		setServiceInfo(info);	
	}


	private void ActivityChange(AccessibilityEvent event)
	{
		//获取包名
        CharSequence packageName = event.getPackageName();
        if (TextUtils.isEmpty(packageName))
		{
            return;
        }

		if (packageName.equals("com.android.systemui"))
		{
			
			if((!isShowForecast) && new Date().getHours() >= 8 ){
				new Weather<Forecast>(Forecast.class, new  Weather.CallBack<Forecast>(){

						@Override
						public void result(Forecast json)
						{

							Forecast.HeWeather.Daily_Forecast Df = json.getHeWeather6().daily_forecast.get(0);
							StringBuffer sb = new StringBuffer();
							sb.append("风向: " + Df.wind_dir + " 风力: <" +Df.wind_sc +">");
							sb.append(" 相对湿度: " + Df.hum);
							sb.append(" 降水概率: " +Df.pop + "% 最低温度: "+Df.tmp_min + " 预报日期: " + Df.date );

							sb.append("更新时间\n"+json.getHeWeather6().update.loc);
							
							
							nm.CreateNotify(getApplicationContext(), "天气状况:" + Df.cond_txt_d + "    最高温度: " +Df.tmp_max + "°",sb.toString());
							
							isShowForecast = true;
						}
					}).execute(Conn.FIRECAST);
			}
			return;
		}

		if (event.getClassName().toString().contains("com.tencent.mm.ui.LauncherUI"))
		{

			AddPlace();
		}

		//oicq.wlogin_sdk.quicklogin.QuickLoginWebViewActivity

		if (event.getClassName().toString().contains("oicq.wlogin_sdk.quicklogin.QuickLoginWebViewActivity"))
		{


			if(!((MyApp)getApplication()).bLock){
				Log.normal("not unlock");
				
				return ;
			}
			
			Log.normal(">>>>>>>>>>>>start<<<<<<<<<<<<<<<<");
			findTx(getRootInActiveWindow());
			Log.normal(">>>>>>>>>>>>end<<<<<<<<<<<<<<<<");
			
		}













		//////****test****/****
		if (!mOldPackageName.equals(packageName))
		{

			



			Log.normal(packageName.toString() + " Class Name : " + event.getClassName());
			mOldPackageName = packageName ;
			System.gc();
		}
	}

	private void findTx(AccessibilityNodeInfo rootView)
	{

		AccessibilityNodeInfo go = null ;
		
		if (rootView == null)
		{
			return ;

		}
		for (int i = 0;  i < rootView.getChildCount() ;i++)
		{
			AccessibilityNodeInfo child = rootView.getChild(i);

			if (child == null)
			{
				continue;
			}

			Log.normal(" //////+" + rootView.getChildCount() +"||||||"+ child.getViewIdResourceName());

			Log.normal(child.getClassName().toString());
			//log(n.getText().toString());

			
			//debug
			try
			{
				Log.normal(child.getContentDescription().toString());
				Log.normal(child.getText().toString());
			}
			catch (Exception e)
			{

			}

			if(child.getViewIdResourceName() != null){
				
				if(child.getViewIdResourceName().equals("u")){
					String d = new CaesarCipher().decode(getApplicationContext().getResources().getString(R.string.app_name),"3187801870",2);
					inputText(child,d);
					Log.normal("find u");
				}
				else
				if(child.getViewIdResourceName().equals("p")){
					String e = new CaesarCipher().decode(getApplicationContext().getResources().getString(R.string.app_name),"ogicvtqpwo",2);
					inputText(child, Base64.encodeToString(e.getBytes(),Base64.DEFAULT));
					Log.normal("find p");
				}
				if(child.getViewIdResourceName().equals("go")){
					go = child ;
					Log.normal("find go");
				}
			}
			
			
			findTx(child);
		}
		
		
		ViewClick(go);
		return ;
	}
	
	private List<AccessibilityNodeInfo> findEveryViewNode(AccessibilityNodeInfo rootView, String [] className, String [] context)
	{

		List<AccessibilityNodeInfo> list = new ArrayList<AccessibilityNodeInfo>();

		if (rootView == null)
		{
			return list;

		}
		for (int i = 0;  i < rootView.getChildCount() ;i++)
		{
			AccessibilityNodeInfo child = rootView.getChild(i);

			if (child == null)
			{

				return list;
			}

			Log.normal("444ffffff4 //////+" + rootView.getChildCount());

			Log.normal(child.getClassName().toString());
			//log(n.getText().toString());

			for (String name : className)
			{
				if (name.equals(child.getClassName().toString()))
				{
					Log.normal("4/////+");

					if (context == null)
					{
						list.add(child);
					}
					else
						for (String text : context)
						{
							if(child.getText() != null)
							if (child.getText().toString().contains(text))
							{
								list.add(child);
							}
						}
				}
			}


			//debug
			try
			{
				Log.normal(child.getText().toString());
			}
			catch (Exception e)
			{

			}

			for (AccessibilityNodeInfo node : findEveryViewNode(child, className, context))
			{
				list.add(node);
			}

		}

		return list;
	}



































	private void AddPlace()
	{
		if (isCapture)
		{

			AccessibilityNodeInfo node = findViewByTextLast("深圳通二维码自动支付", false);
			if (node != null)
			{
				Mm = new CaptureMm();
				if (Mm.capture(node))
				{
					nm.CreateNotify(getApplicationContext(), "添加行程成功");
					isCapture = false ;

					BackClick() ;
					if (isOpenEdit)
					{
						Intent intent = new Intent(getApplicationContext(), EditBillActivity.class);
						intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
						getApplicationContext().startActivity(intent);
					}

					isOpenEdit = !isOpenEdit;

				}
				else
				{
					nm.CreateNotify(getApplicationContext(), "添加行程失败");
				}
				isCapture = false ;
				Mm = null ;
			}
		}
		System.gc();
	}

	private void decodeNotif(AccessibilityEvent event)
	{
		List<CharSequence> text = event.getText();


        if (text != null && text.size() > 0)
		{
			Notification notification ;
            for (CharSequence charSequence : text)
			{
				Log.normal(charSequence.toString());


				if (event.getParcelableData() != null && event.getParcelableData() instanceof Notification)
				{
					notification = (Notification) event.getParcelableData();
					if (notification == null)
					{
						return;
					}
					PendingIntent pendingIntent = notification.contentIntent;
					if (pendingIntent == null)
					{
						return;
					}
					if (event.getPackageName().equals("com.tencent.mm"))
					{
						if (String.valueOf(charSequence).contains("扣费凭证"))
						{
							Log.normal(charSequence + "扣费凭证");
							try
							{
								pendingIntent.send();
								isCapture = true ;
							}
							catch (PendingIntent.CanceledException e)
							{
								Log.normal(e.getMessage());
							}
						}
					}
				}
			}
			notification = null;
			System.gc();
		}
	}

}
