package com.megatronus.ui;
import android.content.Context;
import java.lang.Thread.UncaughtExceptionHandler;
import java.text.SimpleDateFormat;
import com.megatronus.ui.utils.FileManager;
import android.util.Log;

public class CrashHandler implements Thread.UncaughtExceptionHandler
{

	private static CrashHandler mInstance ;

	private Context mContext;

	private Thread.UncaughtExceptionHandler mDefualtCrashHandler;

	@Override
	public void uncaughtException(Thread p1, Throwable p2)
	{
		
		long currentTime = System.currentTimeMillis();
		String crashTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(currentTime);
		StringBuffer sb = new StringBuffer(crashTime + "//////////////////////////////\n");
		sb.append(p2.getLocalizedMessage());
		sb.append(p2.getMessage());
		
		sb.append("\n"+Log.getStackTraceString(p2));//直接将该信息直接写入文件即可);
		
		new FileManager().FileWriter("log.txt",sb.toString() ,true,true);
		
		
		
//		if (mDefualtCrashHandler != null)
//		{
//			//如果系统有默认异常处理就使用它处理
//			mDefualtCrashHandler.uncaughtException(p1, p2);
//		}
//		else
//		{
//			//否则我们自行结束程序
			System.exit(0);
//		}
		
		
		
	}
	
	
	
	public static CrashHandler getInstance() {
		if(mInstance == null){
			mInstance = new CrashHandler();
		}
		return mInstance;
	}

	public void init(Context context) {
		mContext = context.getApplicationContext(); // 确保获得的是系统级的Context
		mDefualtCrashHandler = Thread.getDefaultUncaughtExceptionHandler(); // 获取系统默认的异常处理器
		Thread.setDefaultUncaughtExceptionHandler(this); // 把当前实例设置为系统默认异常处理器
	}
}
