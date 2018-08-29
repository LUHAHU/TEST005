package com.megatronus.ui.utils;

import android.util.Log;
import android.view.accessibility.AccessibilityNodeInfo;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.math.BigDecimal;

public class CaptureMm
{
	
	private long Time ;
	private float money ;
	private boolean APM ; // true AM  ; false PM
	
	private String Date;
	
	private FileManager mFileManager ;
	
	public CaptureMm(){
		mFileManager = new FileManager();
	}
	
	public boolean capture(AccessibilityNodeInfo node)
	{

		if (node == null)
		{
			Log.e(this.getClass().getPackage().getName(),"The Parameter is empty (AccessibilityNodeInfo)" );
			return false;
		}

		AccessibilityNodeInfo parentNode = node.getParent();

		if (parentNode == null)
		{
			Log.e(this.getClass().getPackage().getName(),"Unable to find parent class (AccessibilityNodeInfo)" );
			return false;
		}

		int childCount = parentNode.getChildCount();

		String text ;
		String format = "yyyy-MM-dd HH:mm:ss";
		AccessibilityNodeInfo child = null ;
		for (int i = 0 ; i < childCount ; i++)
		{
			child = parentNode.getChild(i);

			if (child == null)
			{
				return false;
			}

			if (child.getClassName().toString().equals("android.widget.TextView"))
			{


				if (child.getText() != null)
				{
					text = child.getText().toString();
					try
					{
						money =Float.valueOf(text);
						if(money < 1.6f){
							BigDecimal big = new BigDecimal(money);
							BigDecimal big2 = new BigDecimal(1.6f);
							money = big2.subtract(big).add(big).floatValue();
						}
					}
					catch (NumberFormatException ne)
					{}


					

					SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.CHINESE);
					sdf.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
					Date date = null;
					try
					{
						date = sdf.parse(text);
						Time = date.getTime();
						APM = (date.getHours() < 12 );
						
						format = "yyyy-MM-dd" ;
						sdf = new SimpleDateFormat(format, Locale.CHINESE);
					    Date = sdf.format(date);
					}
					catch (ParseException pe)
					{}

					child = null ;
				}
			}
		}
		
		CharSequence str = Date + (APM ? " 上午 " : " 下午 ") + " [_] " + "金额 : 「" + money + "」  <" + Time + ">";
		if(!mFileManager.FileWriter(str,Time)){
			Log.e(this.getClass().getPackage().getName(),"Failed to Write to File");
			return false;
		}
		System.gc();
		return true;
	}
}
