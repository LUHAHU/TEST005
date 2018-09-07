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
	public List<HeWeather> HeWeather6 ;

	public void setHeWeather6(List<HeWeather> heWeather6)
	{
		HeWeather6 = heWeather6;
	}

	public HeWeather getHeWeather6()
	{
		return isValid() ? HeWeather6.get(0) : null;
	}

	public boolean isValid()
	{
		return HeWeather6 == null ? false : true;
	}



	public class HeWeather
	{
		public Basic basic;
		public List<Daily_Forecast> daily_forecast;
		public String status ;
		public update update ;

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


		public class update
		{
			public String loc;
			public String utc;
		}
		public class Daily_Forecast
		{

			public String cond_code_d;
			public String cond_code_n;
			public String cond_txt_d;
			public String cond_txt_n;
			public String date;
			public String hum;
			public String mr;
			public String ms;
			public String pcpn;
			public String pop;
			public String pres;
			public String sr;
			public String ss;
			public String tmp_max;
			public String tmp_min;
			public String uv_index;
			public String vis;
			public String wind_deg;
			public String wind_dir;
			public String wind_sc;
			public String wind_spd;
		}
	}
}
