package com.megatronus.ui;
import android.content.Context;
import java.lang.Thread.UncaughtExceptionHandler;
import java.text.SimpleDateFormat;
import com.megatronus.ui.utils.FileManager;
import android.util.Log;
import android.widget.Toast;
import java.text.ParseException;
import java.util.Date;

public class CrashHandler implements Thread.UncaughtExceptionHandler
{

	private static CrashHandler mInstance ;

	private Context mContext;

	@Override
	public void uncaughtException(Thread p1, Throwable p2)
	{

		long currentTime = System.currentTimeMillis();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String crashTime = sdf.format(currentTime);
		StringBuffer sb = new StringBuffer(crashTime + " :               ");
		sb.append(p2.getLocalizedMessage() + "\n");
		sb.append(p2.getMessage());

		sb.append("\n" + Log.getStackTraceString(p2));//直接将该信息直接写入文件即可);

		String fileName = "" ;

		try
		{
			Date date = sdf.parse(crashTime);
			fileName = String.valueOf(date.getDate());
		}
		catch (ParseException e)
		{
			sb.append(Log.getStackTraceString(e.getCause()));
		}
		finally
		{
			new FileManager().FileWriter(fileName + "log.txt", sb.toString() , true, true);
		}

		Toast.makeText(mContext, "Program exception, will quit", 0).show();
		System.exit(0);



	}



	public static CrashHandler getInstance()
	{
		if (mInstance == null)
		{
			mInstance = new CrashHandler();
		}
		return mInstance;
	}

	public void init(Context context)
	{
		mContext = context.getApplicationContext(); // 确保获得的是系统级的Context
		Thread.setDefaultUncaughtExceptionHandler(this); // 把当前实例设置为系统默认异常处理器
	}
}
