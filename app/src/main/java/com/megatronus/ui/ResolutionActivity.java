package com.megatronus.ui;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import com.megatronus.ui.R;
import com.megatronus.ui.utils.sudo;

public class ResolutionActivity extends AppCompatActivity implements View.OnClickListener ,SeekBar.OnSeekBarChangeListener,View.OnLongClickListener
{

	private TextView DpiText ;
	private TextView ResolutionText ;

	private SeekBar ScreenSizeBar;
	private ImageButton ScreenSizeBtn;
	
	private SeekBar ScreenDpiBar;
	private ImageButton ScreenDpiBtn;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_resolution);
		
		bindView();
	}
	
	public void bindView(){
		ResolutionText = (TextView) findViewById(R.id.ResolutionText);
		ScreenSizeBar = (SeekBar) findViewById(R.id.ScreenSizeSeekBar);
		ScreenSizeBtn = (ImageButton) findViewById(R.id.ScreenSizeBtn);		
		ScreenDpiBar = (SeekBar) findViewById(R.id.screenDpiSeekBar);
		ScreenDpiBtn = (ImageButton) findViewById(R.id.screenDpiBtn);		
		DpiText = (TextView) findViewById(R.id.screenDpiText);
		
		ScreenSizeBtn.setOnClickListener(this);
		ScreenSizeBar.setOnSeekBarChangeListener(this);
		DpiText.setOnClickListener(this);
		ScreenDpiBtn.setOnClickListener(this);
		ScreenDpiBar.setOnSeekBarChangeListener(this);

		ScreenSizeBar.setMax(19);
		ScreenSizeBar.setProgress(9);

		ScreenDpiBar.setMax(19);
		ScreenDpiBar.setProgress(9);
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
	public void onClick(View p1)
	{
		switch(p1.getId()){
			case R.id.ScreenSizeBtn:

				int i = ScreenSizeBar.getProgress();

				Resolution(i, true);
				break ;

			case R.id.screenDpiBtn:

				int dpi =ScreenDpiBar.getProgress();
				ZoomDpi(dpi, true);
				break;
		}}

	@Override
	public boolean onLongClick(View p1)
	{
		// TODO: Implement this method
		return false;
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
}
