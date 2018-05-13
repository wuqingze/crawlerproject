package test;

import java.util.Date;

public class TestDate {

	public static void main(String[] args) {
		String date = new Date().toString();
		String[] afafad = date.split(" ");
		String goodTime = null;
		goodTime = afafad[1]+"/"+afafad[2]+"/"+afafad[5];
		
		System.out.println(goodTime);
	}

}
