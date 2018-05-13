package test;

import com.mysql.jdbc.Connection;

import util.*;

public class TestDBUtil {

	public static void main(String[] args) {
		Connection connection = DBUtil.open();
		if(connection != null)
		{
			System.out.println("success!");
		}
		
		DBUtil.close(connection);
	}

}
