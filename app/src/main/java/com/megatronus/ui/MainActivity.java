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

public class MainActivity extends AppCompatActivity implements View.OnClickListener,SeekBar.OnSeekBarChangeListener,View.OnLongClickListener
{

	private TextView ToolBarMenu ;
	private TextView ResolutionText ;
	private TextView ShowKeyTv ;
	private SeekBar ScreenSizeBar;
	private ImageButton ScreenSizeBtn;

	private TextView DpiText ;
	private SeekBar ScreenDpiBar;
	private ImageButton ScreenDpiBtn;
	private EditText InKeyEd;
	private Button BtnUnLock;

	private String mOriginalCode;

	private String mKey;
	
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

	private void generate()
	{
		mOriginalCode = Base64.encodeToString(String.valueOf(System.currentTimeMillis()).getBytes(), Base64.NO_PADDING | Base64.NO_WRAP) ;
		mKey = new CaesarCipher().encode(getApplicationContext().getPackageName(), mOriginalCode, 5);
		
		ShowKeyTv.setText(mOriginalCode);
	}

	private void initView()
	{
		ToolBarMenu = (TextView) findViewById(R.id.main_setting);
		ResolutionText = (TextView) findViewById(R.id.ResolutionText);
		ScreenSizeBar = (SeekBar) findViewById(R.id.ScreenSizeSeekBar);
		ScreenSizeBtn = (ImageButton) findViewById(R.id.ScreenSizeBtn);		
		BtnUnLock = (Button) findViewById(R.id.mainUnLook);		

		DpiText = (TextView) findViewById(R.id.screenDpiText);
		ScreenDpiBar = (SeekBar) findViewById(R.id.screenDpiSeekBar);
		ScreenDpiBtn = (ImageButton) findViewById(R.id.screenDpiBtn);		
		ShowKeyTv = (TextView) findViewById(R.id.mainTvShowKey);
		InKeyEd = (EditText) findViewById(R.id.mainEtInKey);
		
		ToolBarMenu.setOnClickListener(this);
		ToolBarMenu.setOnLongClickListener(this);
		ScreenSizeBtn.setOnClickListener(this);
		ScreenSizeBar.setOnSeekBarChangeListener(this);
		BtnUnLock.setOnClickListener(this);
		
		DpiText.setOnClickListener(this);
		ScreenDpiBtn.setOnClickListener(this);
		ScreenDpiBar.setOnSeekBarChangeListener(this);

		ScreenSizeBar.setMax(19);
		ScreenSizeBar.setProgress(9);

		ScreenDpiBar.setMax(19);
		ScreenDpiBar.setProgress(9);
		
		generate();
		
	}
	
	
	
	///////*****************************************************///

	public void onClick(View v)
	{

		switch (v.getId())
		{
			case R.id.ScreenSizeBtn:

				int i = ScreenSizeBar.getProgress();

				Resolution(i, true);
				break ;

			case R.id.screenDpiBtn:

				int dpi =ScreenDpiBar.getProgress();
				ZoomDpi(dpi, true);
				break;
			case R.id.main_setting:
				
				this.startActivity(new Intent(this,EditBillActivity.class));
				break ;
			case R.id.mainUnLook:

				if(!mKey.equals(InKeyEd.getText().toString())){
					Toast.makeText(MainActivity.this,"密码错误",0).show();
					break ;
				}
				
				((MyApp)getApplication()).bLock = true;
				
				BtnUnLock.setText("已解锁（#-_-)┯━┯");
				
				handle.postDelayed(new Runnable(){

						@Override
						public void run()
						{
							((MyApp)getApplication()).bLock = false;
							BtnUnLock.setText("解锁 (ﾟДﾟ≡ﾟдﾟ)!");
							Toast.makeText(MainActivity.this,"密码已过期",0).show();
							
							generate();
							
						}
						
					
				},300000);
				
				
				break ;
		}
	}
	

	@Override
	public boolean onLongClick(View p1)
	{
		if(((MyApp)getApplication()).isInit){
			((MyApp)getApplication()).Administrator.isCapture = true ;
			finish();
		}else{
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
	
	public void Resolution(int p2, boolean Set)
	{
		p2 = p2 + 1;

		float size =  ((float)p2 / (float)10);

		int x =(int) (1080 * size);
		int y =(int) (1920 * size);
		ResolutionText.setText(x + "x" + y);

		if (Set)
			sudo.exec("wm size " + x + "x" + y);
	}

	public void ZoomDpi(int dpi , boolean Set)
	{
		dpi++;

		float dpisize = ((float)dpi) / 10.0f;

		dpi = (int )(480 * dpisize);

		DpiText.setText(String.valueOf(dpi));

		if (Set)
			sudo.exec("wm density " + dpi);
	}
	@Override
	public void onProgressChanged(SeekBar p1, int p2, boolean p3)
	{
		switch (p1.getId())
		{

			case R.id.ScreenSizeSeekBar:
				Resolution(p2, false);
				break;
			case R.id.screenDpiSeekBar:
				ZoomDpi(p2, false);
				break;
			default:
				break;
		}
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
