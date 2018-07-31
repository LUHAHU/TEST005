package com.megatronus.ui.service;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import com.megatronus.ui.common.BaseAccessibilityService;
import com.megatronus.ui.utils.CaptureMm;
import com.megatronus.ui.utils.NotifyManager;
import com.megatronus.ui.utils.sudo;
import java.util.List;
import com.megatronus.ui.EditBillActivity;
import com.megatronus.ui.MyApp;

public class AdministratorService extends BaseAccessibilityService
{
	private boolean Capture = false;
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

			if (Capture)
			{
				
				AccessibilityNodeInfo node = findViewByTextLast("深圳通二维码自动支付", false);
				if (node != null)
				{
					Mm = new CaptureMm();
					if (Mm.capture(node))
					{
						nm.CreateNotify(getApplicationContext(), "添加行程成功");
						Capture = false ;
						
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
					Capture = false ;
					Mm = null ;
				}
			}
			System.gc();
		}

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




			log(packageName.toString());
			mOldPackageName = packageName ;
			System.gc();
		}
	}

	private void decodeNotif(AccessibilityEvent event)
	{
		List<CharSequence> text = event.getText();


        if (text != null && text.size() > 0)
		{
			Notification notification ;
            for (CharSequence charSequence : text)
			{
				log(charSequence.toString());


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
							log(charSequence + "扣费凭证");
							try
							{
								pendingIntent.send();
								Capture = true ;
							}
							catch (PendingIntent.CanceledException e)
							{
								log(e.getMessage());
							}
						}
					}
				}
			}
			notification = null;
			System.gc();
		}
	}


	private void log(String text)
	{
		Log.e("AdministratorService", text);
	}
}
