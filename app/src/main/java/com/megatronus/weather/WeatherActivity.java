package com.megatronus.weather;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import com.megatronus.ui.R;
import com.megatronus.weather.bean.Forecast;

public class WeatherActivity extends AppCompatActivity
{
	private TextView weather ;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_weather);
		weather = (TextView) findViewById(R.id.weather_temperature);
		
		
		new Weather<Forecast>(Forecast.class, new  Weather.CallBack<Forecast>(){

				@Override
				public void result(Forecast json)
				{
					weather.setText(json.getHeWeather6().daily_forecast.get(0).tmp_max + "°");
				}
			}).execute(Conn.FIRECAST);
	}
	
}
