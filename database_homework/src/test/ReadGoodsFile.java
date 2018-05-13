package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class ReadGoodsFile {

	public static void main(String[] args) {
		File file = new File("WebRoot//Goods//iphone/bean.txt");
	    try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String tempstrString = reader.readLine();
			while(tempstrString!= null)
			{
				String[] userfulstring = tempstrString.split("=");
				System.out.println(userfulstring[1]);
				tempstrString = reader.readLine();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	}

}
