package com.megatronus.ui;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.megatronus.ui.MainActivity;
import com.megatronus.ui.utils.CaesarCipher;

public class UnLookActivity extends AppCompatActivity implements View.OnClickListener
{

	
	private TextView ShowKeyTv ;



	private EditText InKeyEd;
	private Button BtnUnLock;

	private String mOriginalCode;

	private String mKey;

	private Handler handle ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_unlook);
		
		handle = new Handler();
		
		bindView();
	}
	
	public void bindView(){
		BtnUnLock = (Button) findViewById(R.id.mainUnLook);		



		ShowKeyTv = (TextView) findViewById(R.id.mainTvShowKey);
		InKeyEd = (EditText) findViewById(R.id.mainEtInKey);
		
		BtnUnLock.setOnClickListener(this);




		generate();
	}
	
	private void generate()
	{
		mOriginalCode = Base64.encodeToString(String.valueOf(System.currentTimeMillis()).getBytes(), Base64.NO_PADDING | Base64.NO_WRAP) ;
		mKey = new CaesarCipher().encode(getApplicationContext().getPackageName(), mOriginalCode, 5);

		ShowKeyTv.setText(mOriginalCode);
	}
	
	
	@Override
	public void onClick(View p1)
	{


		if(!mKey.equals(InKeyEd.getText().toString())){
			Toast.makeText(UnLookActivity.this,"密码错误",0).show();
			return;
		}

		((MyApp)getApplication()).bLock = true;

		BtnUnLock.setText("已解锁（#-_-)┯━┯");

		handle.postDelayed(new Runnable(){

				@Override
				public void run()
				{
					((MyApp)getApplication()).bLock = false;
					BtnUnLock.setText("解锁 (ﾟДﾟ≡ﾟдﾟ)!");
					Toast.makeText(UnLookActivity.this,"密码已过期",0).show();

					generate();

				}


			},300000);



	}

	
}
