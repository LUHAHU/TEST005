package com.megatronus.ui.utils;
import java.text.SimpleDateFormat;

public class Log
{

	
	public static void normal(String name, String absolutePath)
	{
		normal(name + ":" + absolutePath);
	}


	public static void normal(String viewIdResourceName)
	{
		log("AdministratorService  :" + viewIdResourceName);
	}
	
	public static void log(String viewIdResourceName)
	{
		String crashTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis());
		StringBuffer sb = new StringBuffer(crashTime + "//////////////////////////////\n");
		sb.append(viewIdResourceName);
		sb.append("\n");
		new FileManager().FileWriter("log.txt",sb.toString() ,true,true);

	}
	
}
