package com.megatronus.weather;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import com.megatronus.ui.R;
import com.megatronus.weather.bean.Forecast;

public class WeatherActivity extends AppCompatActivity
{
	private TextView temperature ;
	private TextView weather ;
	private TextView wind ;
	private TextView hum ;
	
	private TextView msg ;
	private TextView update ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_weather);
		weather = (TextView) findViewById(R.id.weather_weather);
		temperature = (TextView) findViewById(R.id.weather_temperature);
		wind = (TextView) findViewById(R.id.weather_wind);
		hum = (TextView) findViewById(R.id.weather_hum);
		msg = (TextView) findViewById(R.id.weather_msg);
		update = (TextView) findViewById(R.id.weather_update);
		
		new Weather<Forecast>(Forecast.class, new  Weather.CallBack<Forecast>(){

				@Override
				public void result(Forecast json)
				{
					
					Forecast.HeWeather.Daily_Forecast Df = json.getHeWeather6().daily_forecast.get(0);
					temperature.setText(Df.tmp_max + "°");
					weather.setText(Df.cond_txt_d);
					wind.setText(Df.wind_dir + " 风力 " +Df.wind_sc);
					hum.setText("相对湿度  " + Df.hum);
					msg.setText("降水概率   :" +Df.pop + "\n最低温度   :"+Df.tmp_min + "\n预报日期   :" + Df.date );
					
					update.setText("更新时间\n"+json.getHeWeather6().update.loc);
				}
			}).execute(Conn.FIRECAST);
	}
	
}
