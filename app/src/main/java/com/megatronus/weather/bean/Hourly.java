package com.megatronus.weather.bean;
import java.util.List;

public class Hourly
{
	public List<HeWeather> HeWaether6 ;

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
