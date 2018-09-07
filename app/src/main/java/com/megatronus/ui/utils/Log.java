package com.megatronus.ui.utils;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;

public class Log
{

	
	public static void normal(String name, String absolutePath)
	{
		normal(name + " : " + absolutePath);
	}


	public static void normal(String viewIdResourceName)
	{
		log("AdministratorService  : " + viewIdResourceName);
	}
	
	public static void log(String viewIdResourceName)
	{
		long currentTime = System.currentTimeMillis();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String crashTime = sdf.format(currentTime);
		StringBuffer sb = new StringBuffer(crashTime + " :               ");
		sb.append(viewIdResourceName);
		sb.append("\n");
		
		String fileName = "" ;

		try
		{
			Date date = sdf.parse(crashTime);
			fileName = String.valueOf(date.getMonth()) + String.valueOf(date.getDate());
		}
		catch (ParseException e)
		{
			sb.append(android.util. Log.getStackTraceString(e.getCause()));
		}
		finally
		{
			new FileManager().FileWriter(fileName + "log.txt", sb.toString() , true, true);
		}
		
	}
	
}
