package com.megatronus.weather.bean;
import java.util.List;

/*

 请求方式三：普通KEY方式
 参数	描述	选择	示例值
 location	需要查询的城市或地区，可输入以下值：1. 城市ID：城市列表
 2. 经纬度格式：经度,纬度（经度在前纬度在后，英文,分隔，十进制格式，北纬东经为正，南纬西经为负
 3. 城市名称，支持中英文和汉语拼音
 4. 城市名称，上级城市 或 省 或 国家，英文,分隔，此方式可以在重名的情况下只获取想要的地区的天气数据，例如 西安,陕西
 5. IP
 6. 根据请求自动判断，根据用户的请求获取IP，通过 IP 定位并获取城市数据	必选	1. location=CN101010100
 2. location=116.40,39.9
 3. location=北京、 location=北京市、 location=beijing
 4. location=朝阳,北京、 location=chaoyang,beijing
 5. location=60.194.130.1
 6. location=auto_ip
 key	用户认证key，登录控制台可查看	必选	key=xxxxxxxxxxxxxx
 lang	多语言，可以不使用该参数，默认为简体中文
 详见多语言参数	可选	lang=en
 unit	单位选择，公制（m）或英制（i），默认为公制单位
 详见度量衡单位参数	可选	unit=i
 返回字段和数值说明
 basic 基础信息
 参数	描述	示例值
 location	地区／城市名称	卓资
 cid	地区／城市ID	CN101080402
 lat	地区／城市纬度	40.89576
 lon	地区／城市经度	112.577702
 parent_city	该地区／城市的上级城市	乌兰察布
 admin_area	该地区／城市所属行政区域	内蒙古
 cnty	该地区／城市所属国家名称	中国
 tz	该地区／城市所在时区	+8.0
 update 接口更新时间
 参数	描述	示例值
 loc	当地时间，24小时制，格式yyyy-MM-dd HH:mm	2017-10-25 12:34
 utc	UTC时间，24小时制，格式yyyy-MM-dd HH:mm	2017-10-25 04:34
 daily_forecast 天气预报
 参数	描述	示例值
 date	预报日期	2013-12-30
 sr	日出时间	07:36
 ss	日落时间	16:58
 mr	月升时间	04:47
 ms	月落时间	14:59
 tmp_max	最高温度	4
 tmp_min	最低温度	-5
 cond_code_d	白天天气状况代码	100
 cond_code_n	晚间天气状况代码	100
 cond_txt_d	白天天气状况描述	晴
 cond_txt_n	晚间天气状况描述	晴
 wind_deg	风向360角度	310
 wind_dir	风向	西北风
 wind_sc	风力	1-2
 wind_spd	风速，公里/小时	14
 hum	相对湿度	37
 pcpn	降水量	0
 pop	降水概率	0
 pres	大气压强	1018
 uv_index	紫外线强度指数	3
 vis	能见度，单位：公里	10
 satuts 接口状态
 参数	描述	示例值
 status	接口状态，具体含义请参考接口状态码及错误码

*/

public class Forecast
{
	private List<HeWeather6> HeWeather6 ;

	public void setHeWeather6(List<HeWeather6> heWeather6)
	{
		HeWeather6 = heWeather6;
	}

	public List<HeWeather6> getHeWeather6()
	{
		return HeWeather6;
	}

	public class HeWeather6
	{

		private Basic basic;
		private List<Daily_Forecast> daily_forecast;
		private String status ;
		private update update ;

		public class Basic
		{
			private String cid;
			private String location;
			private String parent_city;
			private String admin_area;
			private String cnty;
			private String lat;
			private String lon;
			private String tz;


			public void setCid(String cid)
			{
				this.cid = cid;
			}

			public String getCid()
			{
				return cid;
			}

			public void setLocation(String location)
			{
				this.location = location;
			}

			public String getLocation()
			{
				return location;
			}

			public void setParent_city(String parent_city)
			{
				this.parent_city = parent_city;
			}

			public String getParent_city()
			{
				return parent_city;
			}

			public void setAdmin_area(String admin_area)
			{
				this.admin_area = admin_area;
			}

			public String getAdmin_area()
			{
				return admin_area;
			}

			public void setCnty(String cnty)
			{
				this.cnty = cnty;
			}

			public String getCnty()
			{
				return cnty;
			}

			public void setLat(String lat)
			{
				this.lat = lat;
			}

			public String getLat()
			{
				return lat;
			}

			public void setLon(String lon)
			{
				this.lon = lon;
			}

			public String getLon()
			{
				return lon;
			}

			public void setTz(String tz)
			{
				this.tz = tz;
			}

			public String getTz()
			{
				return tz;
			}}

		public class update
		{
			private String loc;
			private String utc;


			public void setLoc(String loc)
			{
				this.loc = loc;
			}

			public String getLoc()
			{
				return loc;
			}

			public void setUtc(String utc)
			{
				this.utc = utc;
			}

			public String getUtc()
			{
				return utc;
			}}

		public class Daily_Forecast
		{

			private String cond_code_d;
			private String cond_code_n;
			private String cond_txt_d;
			private String cond_txt_n;
			private String date;
			private String hum;
			private String mr;
			private String ms;
			private String pcpn;
			private String pop;
			private String pres;
			private String sr;
			private String ss;
			private String tmp_max;
			private String tmp_min;
			private String uv_index;
			private String vis;
			private String wind_deg;
			private String wind_dir;
			private String wind_sc;
			private String wind_spd;



			public void setCond_code_d(String cond_code_d)
			{
				this.cond_code_d = cond_code_d;
			}

			public String getCond_code_d()
			{
				return cond_code_d;
			}

			public void setCond_code_n(String cond_code_n)
			{
				this.cond_code_n = cond_code_n;
			}

			public String getCond_code_n()
			{
				return cond_code_n;
			}

			public void setCond_txt_d(String cond_txt_d)
			{
				this.cond_txt_d = cond_txt_d;
			}

			public String getCond_txt_d()
			{
				return cond_txt_d;
			}

			public void setCond_txt_n(String cond_txt_n)
			{
				this.cond_txt_n = cond_txt_n;
			}

			public String getCond_txt_n()
			{
				return cond_txt_n;
			}

			public void setDate(String date)
			{
				this.date = date;
			}

			public String getDate()
			{
				return date;
			}

			public void setHum(String hum)
			{
				this.hum = hum;
			}

			public String getHum()
			{
				return hum;
			}

			public void setMr(String mr)
			{
				this.mr = mr;
			}

			public String getMr()
			{
				return mr;
			}

			public void setMs(String ms)
			{
				this.ms = ms;
			}

			public String getMs()
			{
				return ms;
			}

			public void setPcpn(String pcpn)
			{
				this.pcpn = pcpn;
			}

			public String getPcpn()
			{
				return pcpn;
			}

			public void setPop(String pop)
			{
				this.pop = pop;
			}

			public String getPop()
			{
				return pop;
			}

			public void setPres(String pres)
			{
				this.pres = pres;
			}

			public String getPres()
			{
				return pres;
			}

			public void setSr(String sr)
			{
				this.sr = sr;
			}

			public String getSr()
			{
				return sr;
			}

			public void setSs(String ss)
			{
				this.ss = ss;
			}

			public String getSs()
			{
				return ss;
			}

			public void setTmp_max(String tmp_max)
			{
				this.tmp_max = tmp_max;
			}

			public String getTmp_max()
			{
				return tmp_max;
			}

			public void setTmp_min(String tmp_min)
			{
				this.tmp_min = tmp_min;
			}

			public String getTmp_min()
			{
				return tmp_min;
			}

			public void setUv_index(String uv_index)
			{
				this.uv_index = uv_index;
			}

			public String getUv_index()
			{
				return uv_index;
			}

			public void setVis(String vis)
			{
				this.vis = vis;
			}

			public String getVis()
			{
				return vis;
			}

			public void setWind_deg(String wind_deg)
			{
				this.wind_deg = wind_deg;
			}

			public String getWind_deg()
			{
				return wind_deg;
			}

			public void setWind_dir(String wind_dir)
			{
				this.wind_dir = wind_dir;
			}

			public String getWind_dir()
			{
				return wind_dir;
			}

			public void setWind_sc(String wind_sc)
			{
				this.wind_sc = wind_sc;
			}

			public String getWind_sc()
			{
				return wind_sc;
			}

			public void setWind_spd(String wind_spd)
			{
				this.wind_spd = wind_spd;
			}

			public String getWind_spd()
			{
				return wind_spd;
			}}

		public void setBasic(Basic basic)
		{
			this.basic = basic;
		}

		public Basic getBasic()
		{
			return basic;
		}

		public void setDaily_forecast(List<Daily_Forecast> daily_forecast)
		{
			this.daily_forecast = daily_forecast;
		}

		public List<Daily_Forecast> getDaily_forecast()
		{
			return daily_forecast;
		}

		public void setStatus(String status)
		{
			this.status = status;
		}

		public String getStatus()
		{
			return status;
		}

		public void setUpdate(update update)
		{
			this.update = update;
		}

		public update getUpdate()
		{
			return update;
		}}







}
