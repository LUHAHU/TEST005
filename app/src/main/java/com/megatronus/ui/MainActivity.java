package com.megatronus.ui;

import android.widget.*;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import com.megatronus.ui.utils.CaesarCipher;
import com.megatronus.ui.utils.sudo;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import android.os.SystemClock;
import java.text.ParseException;
import android.util.Base64;
import android.os.Handler;
import com.megatronus.ui.utils.FileManager;
import com.megatronus.weather.Weather;
import com.megatronus.weather.bean.Now;
import com.megatronus.weather.Conn;
import com.megatronus.weather.bean.Forecast;
import com.megatronus.weather.WeatherActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,SeekBar.OnSeekBarChangeListener,View.OnLongClickListener
{

	private TextView ToolBarMenu ;

	private Handler handle ;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
		handle = new Handler();


		Log.e("AdministratorService", "start");



		initView();


    }



	private void initView()
	{
		ToolBarMenu = (TextView) findViewById(R.id.main_setting);


		ToolBarMenu.setOnClickListener(this);
		ToolBarMenu.setOnLongClickListener(this);



	}



	///////*****************************************************///

	public void onClick(final View v)
	{

		switch (v.getId())
		{

			case R.id.main_setting:

				this.startActivity(new Intent(this, EditBillActivity.class));
				break ;
			case R.id.activitymainButton1:

				startActivity(new Intent(this, WeatherActivity.class));
				break;

			default:


				startActivity(new Intent(this, ResolutionActivity.class));
				new Weather<Forecast>(Forecast.class, new Weather.CallBack<Forecast>(){

						@Override
						public void result(Forecast json)
						{
							if (json.isValid())
								((Button)v).setText(json.getHeWeather6().daily_forecast.get(0).date + json.getHeWeather6().update.loc);
						}
					}).execute(Conn.FIRECAST);
				break;
		}
	}


	@Override
	public boolean onLongClick(View p1)
	{
		if (((MyApp)getApplication()).isInit)
		{
			((MyApp)getApplication()).Administrator.isCapture = true ;
			finish();
		}
		else
		{
			Toast.makeText(this, "failure!", 0).show();
		}
		goAccess();
		return false;
	}

	public void goAccess()
	{
        Intent intent = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        getApplicationContext().startActivity(intent);
    }




	@Override
	public void onProgressChanged(SeekBar p1, int p2, boolean p3)
	{

	}

	@Override
	public void onStartTrackingTouch(SeekBar p1)
	{
		// TODO: Implement this method
	}

	@Override
	public void onStopTrackingTouch(SeekBar p1)
	{
		// TODO: Implement this method
	}

	@Override
	protected void onDestroy()
	{
		((MyApp)getApplication()).bLock = false ;
		super.onDestroy();
	}

}
