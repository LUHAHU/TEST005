package com.megatronus.ui.utils;

import java.util.*;

public class CaesarCipher
{

	public String encode(String value , String key, int offset)
	{

		String estr="";
		char keyitem;

		for (int i=0;i < key.length();i++)
		{

			keyitem = key.charAt(i);

			if (keyitem >= 'A' && keyitem <= 'Z')
			{

				if (keyitem + offset % 26 <= 'Z')
				{

					estr += (char)(keyitem + offset % 26);
				}
				else
				{

					estr += (char)('A' + ((offset - ('Z' - keyitem) - 1) % 26));


				}
				
			}
			else 
			if (keyitem >= 'a' && keyitem <= 'z')
			{


				if (keyitem + offset % 26 <= 'z')
				{

					estr += (char)(keyitem + offset % 26);

				}
				else
				{

					estr += (char)('a' + ((offset - ('z' - keyitem) - 1) % 26));
				}

			}
			else 
			if (keyitem >= '0' && keyitem <= '9')
			{
				if (keyitem + offset % 10 <= '9')
				{

					estr += (char)(keyitem + offset % 10);

				}
				else
				{

					estr += (char)('0' + ((offset - ('9' - keyitem) - 1) % 10));

				}

			}
			else
			{

				estr += keyitem;
				
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
