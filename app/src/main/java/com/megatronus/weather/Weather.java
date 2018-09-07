package com.megatronus.weather;

import android.os.AsyncTask;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import org.json.JSONException;
import org.json.JSONObject;
import android.widget.TextView;
import com.alibaba.fastjson.JSON;
import java.util.List;
import com.megatronus.weather.bean.Forecast;
import com.megatronus.weather.bean.Now;
import com.megatronus.weather.bean.Hourly;
import com.megatronus.ui.utils.Log;

public class  Weather<T> extends AsyncTask<String,Void,String>
 {
	 private CallBack cb;
	 private Class<?> clazz ;
	 
	 public Weather(Class<?> jsonCla,CallBack callback){
		 clazz = jsonCla ;
		 cb = callback ;
	 }
	 
	@Override
	protected String doInBackground(String... strings) {
		StringBuffer stringBuffer = null;

		try {
			URL url = new URL(strings[0]);
			HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
			InputStream inputStream = null;
			if (httpURLConnection.getResponseCode() == 200) {
				inputStream = httpURLConnection.getInputStream();
				//检测网络异常
			} else {
				return "200";
			}
			InputStreamReader reader = new InputStreamReader(inputStream, "UTF-8");
			BufferedReader bufferedReader = new BufferedReader(reader);
			stringBuffer = new StringBuffer();
			String timp = null;
			while ((timp = bufferedReader.readLine()) != null) {
				stringBuffer.append(timp);
			}
			inputStream.close();
			reader.close();
			bufferedReader.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return stringBuffer.toString();
	}

	@Override
	protected void onPostExecute(String s) {
		super.onPostExecute(s);
		if (s.equals("200")) {

			System.out.println("200");
		}
		
		T Sequ = (T) JSON.parseObject(s,clazz);
		
		Log.normal(s);
		
		cb.result(Sequ);
	}
	
	public interface CallBack<T>{
		void result(T json);
	}
}
