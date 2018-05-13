package daoImp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import bean.Goods;
import bean.User;
import dao.UserADFUL;
import util.DBUtil;

public class UserADFULImp implements UserADFUL{

	@Override
	public void add(User user) {
		Connection connection = DBUtil.open();
		String username = user.getUsername();
		String password = user.getPassword();
		String address = user.getAddress();
		String email = user.getEmail();
		String isLogin = user.getIsLogin();
		
		ArrayList<Goods> tempO = user.getOrders();
		ArrayList<Goods> tempC = user.getShoppingCar();
		
		String orders = null;
		String shoppingCar = null;
		
		if(tempO != null)
		{
			orders = new String();
			Iterator<Goods> orderIterator = tempO.iterator();
			while(orderIterator.hasNext())
			{
				orders += orderIterator.next().getName()+"@";
			}
		}
		if(tempC != null)
		{
			shoppingCar = new String();
			Iterator<Goods> shoppingCarIterator = tempC.iterator();
			while(shoppingCarIterator.hasNext())
			{
				shoppingCar += shoppingCarIterator.next().getName()+"@";
			}
		}
		
		String sql = "insert into user(username,password,address,email,isLogin,orders,shoppingCar)"
				+ "values(?,?,?,?,?,?,?);";
		
		try {
			PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			preparedStatement.setString(3, address);
			preparedStatement.setString(4, email);
			preparedStatement.setString(5, isLogin);
			preparedStatement.setString(6, orders);
			preparedStatement.setString(7, shoppingCar);
			
			preparedStatement.execute();
			
		    ResultSet resultSet = preparedStatement.executeQuery("select id from user where username='"+username+"';");
		    if(resultSet.next())
		    {
		    	user.setId(resultSet.getInt(1));
		    }
		} catch (Exception e) {
			e.printStackTrace();
		} 
		DBUtil.close(connection);
	}

	@Override
	public void delete(User user) {
		Connection connection = DBUtil.open();
		String username = user.getUsername();
		String sql = "delete from user where username='"+username+"';";
		try {
			Statement statement = (Statement) connection.createStatement();
			statement.execute(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		DBUtil.close(connection);
	}

	@Override
	public User find(String name) {
		Connection connection = DBUtil.open();
		User user = null;
		String sql = "select * from user where username='"+name+"';";
		try {
			Statement statement = (Statement) connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			if(resultSet.next())
			{
				user = new User();
						
				int id = resultSet.getInt(1);                      
				String username = resultSet.getString(2);               
				String password = resultSet.getString(3);               
				String address = resultSet.getString(4);                
				String email = resultSet.getString(5);                  
				String isLogin = resultSet.getString(6);   
				String tempOrders = resultSet.getString(7);
				String tempShoppingCar = resultSet.getString(8);
				ArrayList<Goods> orders = null;       
				ArrayList<Goods> shoppingCar = null;
				
				if(tempOrders != null)
				{
					String[] ordersName = tempOrders.split("@");
					if(ordersName.length>0)
					{
						orders = new ArrayList<Goods>();
						for(int i=0; i<ordersName.length; i++)
						{
							Goods goods = new Goods();
							goods.setName(ordersName[i]);
							orders.add(goods);
						}	
					}
				}
				
				if(tempShoppingCar != null)
				{
					String[] shoppingCarName = tempShoppingCar.split("@");
					if(shoppingCarName.length>0)
					{
						shoppingCar = new ArrayList<Goods>();
						for(int i=0; i<shoppingCarName.length; i++)
						{
							Goods goods = new Goods();
							goods.setName(shoppingCarName[i]);
							shoppingCar.add(goods);
						}
					}
				}
				
				
				user.setId(id);
				user.setUsername(username);
				user.setPassword(password);
				user.setAddress(address);
				user.setEmail(email);
				user.setIsLogin(isLogin);
				user.setOrders(orders);
				user.setShoppingCar(shoppingCar);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		DBUtil.close(connection);                           
		return user;
	}

	@Override
	public void update(User user) {
		Connection connection = DBUtil.open();
		
		String password = user.getPassword();
		String address = user.getAddress();
		String email = user.getEmail();
		String isLogin = user.getIsLogin();
		
		ArrayList<Goods> tempO = user.getOrders();
		ArrayList<Goods> tempC = user.getShoppingCar();
		
		String orders = null;
		String shoppingCar = null;
		
		if(tempO != null)
		{
			orders = new String();
			Iterator<Goods> orderIterator = tempO.iterator();
			while(orderIterator.hasNext())
			{
				orders += orderIterator.next().getName()+"@";
			}
		}
		if(tempC != null)
		{
			shoppingCar = new String();
			Iterator<Goods> shoppingCarIterator = tempC.iterator();
			while(shoppingCarIterator.hasNext())
			{
				shoppingCar += shoppingCarIterator.next().getName()+"@";
			}
		}
		String sql = "update user set password=?,address=?,email=?,isLogin=?,orders=?,"
				+"shoppingCar=?";
		try {
			PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
			preparedStatement.setString(1,password );
			preparedStatement.setString(2,address );
			preparedStatement.setString(3, email);
			preparedStatement.setString(4,isLogin );
			preparedStatement.setString(5, orders);
			preparedStatement.setString(6, shoppingCar);
			
			preparedStatement.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
		DBUtil.close(connection);
	}

	@Override
	public ArrayList<User> list() {
		ArrayList<User> userList = new ArrayList<User>();
		Connection connection = DBUtil.open();
		User user = null;
		String sql = "select * from user;";
		try {
			Statement statement = (Statement) connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			while(resultSet.next())
			{ 
				user = new User();
				int id = resultSet.getInt(1);                      
				String username = resultSet.getString(2);               
				String password = resultSet.getString(3);               
				String address = resultSet.getString(4);                
				String email = resultSet.getString(5);                  
				String isLogin = resultSet.getString(6);   
				String tempOrders = resultSet.getString(7);
				String tempShoppingCar = resultSet.getString(8);
				ArrayList<Goods> orders = null;       
				ArrayList<Goods> shoppingCar = null;
				
				if(tempOrders != null)
				{
					String[] ordersName = tempOrders.split("@");
					if(ordersName.length>0)
					{
						orders = new ArrayList<Goods>();
						for(int i=0; i<ordersName.length; i++)
						{
							Goods goods = new Goods();
							goods.setName(ordersName[i]);
							orders.add(goods);
						}	
					}
				}
				
				if(tempShoppingCar != null)
				{
					String[] shoppingCarName = tempShoppingCar.split("@");
					if(shoppingCarName.length>0)
					{
						shoppingCar = new ArrayList<Goods>();
						for(int i=0; i<shoppingCarName.length; i++)
						{
							Goods goods = new Goods();
							goods.setName(shoppingCarName[i]);
							shoppingCar.add(goods);
						}
					}
				}
				
				
				user.setId(id);
				user.setUsername(username);
				user.setPassword(password);
				user.setAddress(address);
				user.setEmail(email);
				user.setIsLogin(isLogin);
				user.setOrders(orders);
				user.setShoppingCar(shoppingCar);
				
				
				userList.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		DBUtil.close(connection);    
		return userList;
	}

}
