package com.megatronus.ui.utils;

import java.util.*;

class CaesarCipher
{

	public String encode(String value ,String key, int offset)
	{
		
		String estr="";
		char keyitem;
		
		for (int i=0;i < key.length();i++){

			System.out.println();
			
			keyitem = key.charAt(i);
			
			if (keyitem >= 'A' && keyitem <= 'Z'){
				
				System.out.println("Key Item [ " + keyitem + " ] greater or equal to A(65) and Less than or equal to Z(90) \n{");
				System.out.println(String.format("Key Item +N : %d , Key Item +N Modulus Total %d , Compared %s",keyitem + offset , keyitem + offset % 26, (keyitem + offset % 26 <= 'Z') ? "Less than or equal to Z(90)":"more than the Z(90)"));
				if (keyitem + offset % 26 <= 'Z'){

					estr += (char)(keyitem + offset % 26);
					int T1 = keyitem + offset % 26 ;
					System.out.println("Usr Key Item + N % 26 : [" + (char)T1+"("+T1+")]");
				}else{

					estr += (char)('A' + ((offset - ('Z' - keyitem) - 1) % 26));

					int t1 = 'Z' - keyitem;
					int t2 = offset - t1 - 1;
					int t3 = t2 % 10;
					int t4 = 'A' + t3 ;
					System.out.println(String.format("Z(90) - Key Item : %d(T1) , N - T1 - 1 : %d(T2) , T2 Modulus 10: %d(T3) , A(65) + T3 : %d(T4) , CHAR : %s,", t1, t2, t3, t4,(char)t4));
					
				}
			System.out.println("}");
			}else 
			if (keyitem >= 'a' && keyitem <= 'z')	{
				
				System.out.println("Key Item [ " + keyitem + " ] greater or equal to a(97) and Less than or equal to z(122) \n{");
				
				if (keyitem + offset % 26 <= 'z'){

					estr += (char)(keyitem + offset % 26);
					int T1 = keyitem + offset % 26 ;
					System.out.println("Usr Key Item + N % 26 : [" + (char)T1+"("+T1+")]");
				
					}	else{

					estr += (char)('a' + ((offset - ('z' - keyitem) - 1) % 26));
					int t1 = 'z' - keyitem;
					int t2 = offset - t1 - 1;
					int t3 = t2 % 10;
					int t4 = 'a' + t3 ;
					System.out.println(String.format("z(122) - Key Item : %d(T1) , N - T1 - 1 : %d(T2) , T2 Modulus 10: %d(T3) , a(97) + T3 : %d(T4) , CHAR : %s,", t1, t2, t3, t4,(char)t4));
					

				}
				System.out.println("}");

			}else 
			if (keyitem >= '0' && keyitem <= '9'){
				System.out.println("Key Item [ " + keyitem + " ] greater or equal to 0(48) and Less than or equal to 9(57) \n{");
				System.out.println(String.format("Key Item +N : %d , Key Item +N Modulus Total %d , Compared %s",keyitem + offset , keyitem + offset % 10, (keyitem + offset % 10 <= '9') ? "Less than or equal to 9(57)":"more than the 9(57)"));
				if (keyitem + offset % 10 <= '9')
				{

					estr += (char)(keyitem + offset % 10);

					System.out.println(String.format("Key Item +N %d(T1) , T1 Modulus 10 : %d",keyitem + offset  ,keyitem + offset % 10));
				}
				else
				{

					estr += (char)('0' + ((offset - ('9' - keyitem) - 1) % 10));

					int t1 = '9' - keyitem;
					int t2 = offset - t1 - 1;
					int t3 = t2 % 10;
					int t4 = '0' + t3 ;
					System.out.println(String.format("9(57) - Key Item : %d(T1) , N - T1 - 1 : %d(T2) , T2 Modulus 10: %d(T3) , 0(48) + T3 : %d(T4) , CHAR : %s,", t1, t2, t3, t4,(char)t4));
					
				}
				System.out.println("}");
			}else{

				estr += keyitem;
				System.out.print("Incomprehensible char ");
				System.out.println(keyitem);

			}

		}

return estr;
}



/******





















*****/




	public String decode(String path, String key, int offset)
	{
		String estr="";
		char keyitem;
		

		for (int i=0;i < key.length();i++)

		{

			keyitem = key.charAt(i);

			if (keyitem >= 'A' && keyitem <= 'Z')

			{

				if (keyitem - offset % 26 >= 'A')

				{

					estr += (char)(keyitem - offset % 26);

				}

				else

				{

					estr += (char)('Z' - ((offset - (keyitem - 'A') - 1) % 26));

				}

			}

			else if (keyitem >= 'a' && keyitem <= 'z')

			{

				if (keyitem - offset % 26 >= 'a')

				{

					estr += (char)(keyitem - offset % 26);

				}

				else

				{

					estr += (char)('z' - ((offset - (keyitem - 'a') - 1) % 26));

				}

			}

			else if (keyitem >= '0' && keyitem <= '9')

			{

				if (keyitem - offset % 10 >= '0')

				{

					estr += (char)(keyitem - offset % 10);

				}

				else

				{

					estr += (char)('9' - ((offset - (keyitem - '0') - 1) % 10));

				}

			}

			else

			{

				estr += keyitem;

			}

		}
	return estr;
	}   

}
