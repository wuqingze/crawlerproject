package daoImp;

import java.sql.ResultSet;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import bean.Customer;
import util.DBUtil;

public class UserVerify
{
	public static boolean find(String name) {
		Connection connection = DBUtil.open();
		boolean findResult = false;
		String sql = "select * from Customer where name='"+name+"';";
		try {
			Statement statement = (Statement) connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			if(resultSet.next())
			{
				findResult = true;
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
		DBUtil.close(connection);
		return findResult;
	}
	
	
	public static Customer getCustomer(String name)
	{
		Connection connection = DBUtil.open();
		boolean findResult = false;
		String sql = "select * from Customer where name='"+name+"';";
		try {
			Statement statement = (Statement) connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			if(resultSet.next())
			{
				Customer customer = new Customer();
				customer.setAddress(resultSet.getString("address"));
				customer.setCity(resultSet.getString("city"));
				customer.setCustomerID(resultSet.getString("customerID"));
				customer.setEmail(resultSet.getString("email"));
				customer.setName(resultSet.getString("name"));
				customer.setPassword(resultSet.getString("password"));
				customer.setPhone(resultSet.getString("phone"));
				customer.setPostcode(resultSet.getString("postcode"));
				customer.setSex(resultSet.getString("sex"));
				
				return customer;
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
		DBUtil.close(connection);
		return null;
	}
}
