package com.megatronus.ui.utils;

import java.io.*;

public class sudo
{

	public static String exec(String sh)

{
	java.lang.Process psProcess = null;
	try {
		psProcess = Runtime.getRuntime().exec("su");
	} catch (IOException e) {
		e.printStackTrace();
	}
	DataOutputStream out = new DataOutputStream(psProcess.getOutputStream());
	InputStream is = psProcess.getInputStream();

	try {
		out.writeBytes(sh);
		out.flush();
	} catch (IOException e) {
		e.printStackTrace();
	}

	try {
		out.writeBytes("exit\n");
		out.flush();
		psProcess.waitFor();
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
	String re="";
	try {
		if (is.read() != 0) {
			int available = is.available();
			byte[] characters = new byte[available + 1];
			is.read(characters, 1, available);
			re = new String(characters);
		}
	} catch (IOException e) {
		e.printStackTrace();
	}
	return re;
	

	}
}
