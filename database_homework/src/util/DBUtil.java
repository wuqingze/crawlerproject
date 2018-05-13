package util;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

public class DBUtil {
	public static String database = "database_project";
//	public static String database = "test11";
		
	public static Connection open()
    {
    	try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
    	try {
			Connection connection = (Connection) DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/"+database,
					"root","root");
			return connection;
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return null;
    }
    
    public static void close(Connection connection)
    {
    	try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    
    
    public static void main(String[] args) {
		open();
	}
}
