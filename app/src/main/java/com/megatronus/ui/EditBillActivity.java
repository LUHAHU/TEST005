package com.megatronus.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import com.megatronus.ui.utils.FileManager;
import android.widget.TextView;
import android.view.Window;

public class EditBillActivity extends AppCompatActivity implements OnItemClickListener , View.OnLongClickListener
{

	private ListView mListView;
	private EditText mEdit ;
	private TextView MoneyText ;
	
	private FileManager fm ;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_editbill);

		setTitle("");
		
		fm = new FileManager();

		mListView = (ListView) findViewById(R.id.editbillListView);
		mEdit = (EditText) findViewById(R.id.editbillEt);
		MoneyText = (TextView) findViewById(R.id.editBillMoneyTv);
		
		mListView.setOnItemClickListener(this);
		MoneyText.setOnLongClickListener(this);
		if (fm.isNeedEdit())
		{
			String [] array = fm.FileReadPreset();

			if (array != null)
			{
				ArrayAdapter aAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, array);
				mListView.setAdapter(aAdapter);
			}
			else
			{
				Toast.makeText(this, "Failed to read file", 0).show();
			}
		}
		else
		{
			mEdit.setVisibility(View.GONE);
			MoneyText.setText(String.valueOf(fm.FileReadMoney()));
			
			String [] array = fm.FileReader();

			if (array != null)
			{
				ArrayAdapter aAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, array);
				mListView.setAdapter(aAdapter);
			}
			else
			{
				Toast.makeText(this, "Failed to read file", 0).show();
			}
		}

		System.gc();
	}

	public void onClick(View v)
	{
		if (!mEdit.getText().toString().isEmpty())
		{
			if (fm.FileWritPreset(mEdit.getText().toString()))
			{
				if (fm.ReplaceText(mEdit.getText().toString()))
				{
					Toast.makeText(this, "File save and set successfully!", 0).show();
					finish();
				}
				else
				{
					Toast.makeText(this, "Failed edit text !", 0).show();
				}
			}
			else
			{
				Toast.makeText(this, "Failed to add new text to file !", 0).show();
			}
		}
		else
		{
			Toast.makeText(this, "please enter text or select preset item!", 0).show();
		}

		System.gc();

	}


	@Override
	public boolean onLongClick(View p1)
	{
		if(((MyApp)getApplication()).isInit){
			((MyApp)getApplication()).Administrator.isOpenEdit = true ;
			finish();
		}else{
			Toast.makeText(this, "Skip failure!", 0).show();
		}
		return false;
	}
	
	@Override
	public void onItemClick(AdapterView<?> p1, View p2, int p3, long p4)
	{
		CharSequence text = ((TextView)p2).getText();

		if (text == null)
		{
			Toast.makeText(this, "select error !", 0).show();
			return ;
		}

		if (fm.ReplaceText(text))
		{
			Toast.makeText(this, "replace text successfully in the file!", 0).show();
			finish();
		}
		else
		{
			Toast.makeText(this, "Did not find the text to be edited in the file", 0).show();
		}

		System.gc();
	}

}
