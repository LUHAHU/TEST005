package com.megatronus.weather.bean;
import java.util.List;

/*
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
 location	地区／城市名称	海淀
 cid	地区／城市ID	CN101080402
 lat	地区／城市纬度	39.956074
 lon	地区／城市经度	116.310316
 parent_city	该地区／城市的上级城市	北京
 admin_area	该地区／城市所属行政区域	北京
 cnty	该地区／城市所属国家名称	中国
 tz	该地区／城市所在时区	+8.0
 update 接口更新时间
 参数	描述	示例值
 loc	当地时间，24小时制，格式yyyy-MM-dd HH:mm	2017-10-25 12:34
 utc	UTC时间，24小时制，格式yyyy-MM-dd HH:mm	2017-10-25 04:34
 now 实况天气
 参数	描述	示例
 fl	体感温度，默认单位：摄氏度	23
 tmp	温度，默认单位：摄氏度	21
 cond_code	实况天气状况代码	100
 cond_txt	实况天气状况描述	晴
 wind_deg	风向360角度	305
 wind_dir	风向	西北
 wind_sc	风力	3
 wind_spd	风速，公里/小时	15
 hum	相对湿度	40
 pcpn	降水量	0
 pres	大气压强	1020
 vis	能见度，默认单位：公里	10
 cloud	云量	23
 status 接口状态
 参数	描述	示例值
 status	接口状态，具体含义请参考接口状态码及错误码	ok
*/

public class Now
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
		public Basic basic;
		public Now now;
		public String status ;
		public Update update ;

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
			
		public class Now
		{
			public String cond_code;
			public String cond_txt;
			public String fl;
			public String hum;
			public String pcpn;
			public String pres;
			public String tmp;
			public String vis;
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
