package com.megatronus.ui.utils;

import android.os.Environment;
import android.util.Log;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.BufferedInputStream;
import android.icu.math.BigDecimal;

public class FileManager
{
	private File storagePath ;

	public FileManager()
	{
		storagePath = new File(Environment.getExternalStorageDirectory(), "Bill");
		if (!storagePath.exists())
		{
			if (storagePath.mkdirs())
			{
				Log.e(this.getClass().getPackage().getName(), "Fount that the target file is empty and new folder failed :"
					  + storagePath.getAbsolutePath());
			}
		}
	}

	public boolean FileWriter(CharSequence str, long time)
	{
		StringBuffer sb = FileReader("bill.txt");

		if(sb != null){
			if((sb.toString().contains(String.valueOf(time)))){
				Log.e(this.getClass().getPackage().getName(), "Did not find the text to be edited in the file");
				return false;
			}
		}
		
		return FileWriter(str);
	}

	public String [] FileReadPreset()
	{
		StringBuffer sb = FileReader("preset.conf");
		
		if(sb == null){
			return null;
		}
		
		String st = sb.toString();
		
		
		String [] split = st.split("\n");
		
		return split ;
	}

	public boolean FileWriter(CharSequence text){
		return FileWriter("bill.txt",text,true,true);
	}
	
	public boolean FileWriterReplace(CharSequence text){
		return FileWriter("bill.txt",text,false,false);
	}
	
	public boolean FileWritPreset(CharSequence text){
		return FileWriter("preset.conf",text,true,true);
	}
	
	public boolean isNeedEdit(){
		StringBuffer sb = FileReader("bill.txt");

		if(sb == null){
			Log.e(this.getClass().getPackage().getName(), "Did not read the file");
			return false;
		}

		if(!(sb.toString().contains("[_]"))){
			Log.e(this.getClass().getPackage().getName(), "Did not find the text to be edited in the file");
			return false;
		}
		
		return true ;
	}
	
	public String [] FileReader(){
		StringBuffer sb = FileReader("bill.txt");

		if(sb == null){
			Log.e(this.getClass().getPackage().getName(), "Did not read the file");
			return null;
		}
		String st = sb.toString().replaceAll("<.*>","");
		
		return st.split("\n");
	}
	
	public float FileReadMoney(){
		StringBuffer sb = FileReader("bill.txt");

		if(sb == null){
			Log.e(this.getClass().getPackage().getName(), "Did not read the file");
			return -1.f;
		}
		String [] st = sb.toString().split("\n");
		
		if(st == null){
			return -1.f;
		}
		
		BigDecimal big = new BigDecimal("0");
		for(String s : st){
			String t = s.substring(s.indexOf("「") + 1,s.lastIndexOf("」"));
		
			BigDecimal big2 = new BigDecimal(t);
			big = big.add(big2);
		}
		return big.floatValue();
	}
	
	public boolean ReplaceText(CharSequence newChar){
		StringBuffer sb = FileReader("bill.txt");
		
		if(sb == null){
			Log.e(this.getClass().getPackage().getName(), "Did not read the file");
			return false;
		}
		
		if(!(sb.toString().contains("[_]"))){
			Log.e(this.getClass().getPackage().getName(), "Did not find the text to be edited in the file");
			return false;
		}
		
		String after = sb.toString().replace("[_]",newChar);
		
		if(after == null){
			Log.e(this.getClass().getPackage().getName(), "Did not find the text to be edited in the file");
			return false;
		}
		
		if(after.isEmpty()){
			Log.e(this.getClass().getPackage().getName(), "Did not find the text to be edited in the file");
			return false;
		}
		
		FileWriterReplace(after);
		
		return true;
	}
	
	public boolean FileWriter(String fileName,CharSequence text,boolean isAppend,boolean isAppendEnter)
	{

		if (!(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)))
		{
			Log.e(this.getClass().getPackage().getName(), "storage no mount");
			return false ;
		}


		if (!storagePath.exists())
		{
			Log.e(this.getClass().getPackage().getName(), "storage does not exist");
			return false;
        }

		FileWriter fw = null ;

        try
		{
            File document = new File(storagePath, fileName);

			if (!document.exists())
			{
				if (!document.createNewFile())
				{
					Log.e(this.getClass().getPackage().getName(), "New File (document) Failed");
					return false;
				}
			}

			fw = new FileWriter(document, isAppend);

            fw.append(text);
			
			if(isAppendEnter)
			fw.append("\n");

		}
		catch (IOException e)
		{
			Log.e(this.getClass().getPackage().getName(), e.getMessage());
            e.printStackTrace();
        }
		finally
		{
			try
			{
				if (fw != null)
					fw.close();

			}
			catch (IOException e)
			{e.printStackTrace();}
		}

		System.gc();
		return true ;
	}

	public StringBuffer FileReader(String fileName)
	{

		if (!(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)))
		{
			Log.e(this.getClass().getPackage().getName(), "storage no mount");
			return null ;
		}


		if (!storagePath.exists())
		{
			Log.e(this.getClass().getPackage().getName(), "storage does not exist");
			return null;
        }


		FileReader fr = null;
		StringBuffer sb  = new StringBuffer();

		File document = new File(storagePath, fileName);

		try
		{
			if (!document.exists())
			{
				Log.e(this.getClass().getPackage().getName(), "File (document) not exist");
				return null;
			}

			fr = new FileReader(document);

			char [] cbuf = new char[1024];
			int len = 0;
			while ((len = fr.read(cbuf)) != -1)
			{
				sb.append(cbuf, 0, len);
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
			Log.e(this.getClass().getPackage().getName(), e.getMessage());
		}finally{
			if(fr!=null){
				try
				{
					fr.close();
				}
				catch (IOException e)
				{e.printStackTrace();}
			}
		}

		System.gc();
		return sb ;
	}
}
