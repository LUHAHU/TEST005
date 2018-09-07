package com.megatronus.weather.bean;
import java.util.List;

public class Hourly
{
	private List<HeWeather> HeWeather6 ;

	public void setHeWeather6(List<HeWeather> heWeather6)
	{
		HeWeather6 = heWeather6;
	}

	public HeWeather getHeWeather6()
	{
		return isValid() ? HeWeather6.get(0) : null;
	}
	
	
	public boolean isValid(){
		return HeWeather6 == null ? false : true;
	}
	
    public class HeWeather
	{
		public Basic basic ;
		public List<Hourly> hourly;
		public String status;
		public Update update;

		public class Basic
		{
			public String cid;
			public String location;
			public String parent_city;
			public String admin_area;
			public String cnty;
			public String lat;
			public String lon;
			public String tz;
		}

		public class Hourly
		{
			public String cloud;
			public String cond_code;
			public String cond_txt;
			public String hum;
			public String pop;
			public String pres;
			public String time;
			public String tmp;
			public String wind_deg;
			public String wind_dir;
			public String wind_sc;
			public String wind_spd;
		}

		public class  Update
		{
			public String loc;
			public String utc;
		}
	}
}
