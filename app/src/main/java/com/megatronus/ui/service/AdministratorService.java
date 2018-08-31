package com.megatronus.ui.service;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.text.TextUtils;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import com.megatronus.ui.common.BaseAccessibilityService;
import com.megatronus.ui.utils.CaptureMm;
import com.megatronus.ui.utils.NotifyManager;
import com.megatronus.ui.utils.sudo;
import java.util.List;
import com.megatronus.ui.EditBillActivity;
import com.megatronus.ui.MyApp;
import android.webkit.WebView;
import android.accessibilityservice.AccessibilityServiceInfo;
import java.util.ArrayList;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.util.Base64;
import com.megatronus.ui.utils.CaesarCipher;
import com.megatronus.ui.R;
import com.megatronus.ui.utils.Log;

public class AdministratorService extends BaseAccessibilityService
{
	public boolean isCapture = false;
	public boolean isOpenEdit = false ;

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
			
			
			inputText(findTx(getRootInActiveWindow(),"u"),"114144141414");
			
			inputText(findTx(getRootInActiveWindow(),"p"),"114144141414");

			Log.normal(">>>>>>>>>>>>start<<<<<<<<<<<<<<<<");
			try
			{
			Thread.sleep(500);
			}
			catch (InterruptedException e) {}
			

			int flag = 0 ;
			List<AccessibilityNodeInfo> nodearr = findEveryViewNode(getRootInActiveWindow(), new String[]{EditText.class.getName(),}, null);
			for (AccessibilityNodeInfo i : nodearr)
			{
				Log.normal("fff" + i.getViewIdResourceName());

				if (i.getViewIdResourceName().equals("u"))
				{
					String d = new CaesarCipher().decode(getApplicationContext().getResources().getString(R.string.app_name),"3187801877",2);
					//inputText(i, d);
					Log.normal("find u");
					flag ++ ;
				}

				try
			{
			Thread.sleep(300);
			}
			catch (InterruptedException e) {}
			
				
				if (i.getViewIdResourceName().equals("p"))
				{
					String e = new CaesarCipher().decode(getApplicationContext().getResources().getString(R.string.app_name),"ogicvtqpwu",2);
					//inputText(i, Base64.encodeToString(e.getBytes(),Base64.DEFAULT));
					Log.normal("find p");
					flag ++;
				}
			}

			try
			{
			Thread.sleep(200);
			}
			catch (InterruptedException e) {}
			
			if (flag == 2)
			{
				for (AccessibilityNodeInfo login : findEveryViewNode(getRootInActiveWindow(), new String[]{View.class.getName()}, new String[]{"登 录"}))
				{
					//ViewClick(login);
					Log.normal("login");
				}
			}
		}













		//////****test****/****
		if (!mOldPackageName.equals(packageName))
		{

			if (packageName.equals("com.tencent.qqmusicpad"))
			{

				sudo.exec("wm size 1404x2496");
			}
			else if (mOldPackageName.equals("com.tencent.qqmusicpad"))
			{

				sudo.exec("wm size 1080x1920");
			}




			Log.normal(packageName.toString() + " Class Name : " + event.getClassName());
			mOldPackageName = packageName ;
			System.gc();
		}
	}

	private AccessibilityNodeInfo findTx(AccessibilityNodeInfo rootView,String id)
	{

		if (rootView == null)
		{
			return null;

		}
		for (int i = 0;  i < rootView.getChildCount() ;i++)
		{
			AccessibilityNodeInfo child = rootView.getChild(i);

			if (child == null)
			{

				return null;
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

			if(child.getViewIdResourceName().equals(id)){
				return child;
			}else{
				return findTx(child,id);
			}
		}
		
		return null;
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
