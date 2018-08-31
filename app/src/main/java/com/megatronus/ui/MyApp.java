package com.megatronus.ui;

import android.app.Application;
import android.content.Context;
import com.megatronus.ui.service.AdministratorService;

public class MyApp extends Application {

    public AdministratorService Administrator ;
	public boolean isInit ;
	public boolean bLock ;
	
    @Override
    public void onCreate() {
        super.onCreate();
		CrashHandler.getInstance().init(this);
		
     }
}
