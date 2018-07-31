package com.megatronus.ui.utils;

import android.util.Log;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class sudo
{
	public static String exec(String sh)
	{
        String result = "";
        DataOutputStream dos = null;
        DataInputStream dis = null;

        try
		{
            Process p = Runtime.getRuntime().exec("su");// 经过Root处理的android系统即有su命令
            dos = new DataOutputStream(p.getOutputStream());
            dis = new DataInputStream(p.getInputStream());


            dos.writeBytes(sh + "\n");
            dos.flush();
            dos.writeBytes("exit\n");
            dos.flush();
            String line = null;
            while ((line = dis.readLine()) != null)
			{
                Log.d("result", line);
                result += line;
            }
            p.waitFor();
        }
		catch (Exception e)
		{
            e.printStackTrace();
        }
		finally
		{
            if (dos != null)
			{
                try
				{
                    dos.close();
                }
				catch (IOException e)
				{
                    e.printStackTrace();
                }
            }
            if (dis != null)
			{
                try
				{
                    dis.close();
                }
				catch (IOException e)
				{
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

}
