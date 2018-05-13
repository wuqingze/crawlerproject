package daoImp;

import java.awt.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import bean.Goods;
import dao.GoodsADFUL;
import util.DBUtil;

public class GoodsADFULImp implements GoodsADFUL{

	@Override
	public void add(Goods goods) {
		Connection connection = DBUtil.open();
		
		 String name = goods.getName();
		 String introduction = goods.getIntroduction();
		 String details = goods.getDetails();
		 int count = goods.getCount();
		 double price = goods.getPrice();
		 int saleCount = goods.getSaleCount();
		 String pictures = goods.getPictures();
		 String category = goods.getCategory();
		 
		 String sql = "insert into goods(name,introduction,details,count,price,saleCount,pictures,category)"
		 		+ " values(?,?,?,?,?,?,?,?);";
		try {
			PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, introduction);
			preparedStatement.setString(3, details);
			preparedStatement.setInt(4, count);
			preparedStatement.setDouble(5, price);
			preparedStatement.setInt(6, saleCount);
			preparedStatement.setString(7, pictures);
			preparedStatement.setString(8, category);
			
			preparedStatement.execute();
			
		    ResultSet resultSet = preparedStatement.executeQuery("select id from goods where name='"+name+"';");
		    if(resultSet.next())
		    {
		    	goods.setId(resultSet.getInt(1));
		    }
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		DBUtil.close(connection);
	}

	@Override
	public void delete(Goods goods) {
		
		Connection connection = DBUtil.open();
		String name = goods.getName();
		String sql = "delete from goods where name='"+name+"';";
		
		try {
			Statement statement = (Statement) connection.createStatement();
			statement.execute(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		DBUtil.close(connection);
	}

	@Override
	public Goods find(String name) {
		Goods goods = null;
		Connection connection = DBUtil.open();
		String sql = "select * from goods where name='"+name+"';";
		try {
			Statement statement = (Statement) connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			if(resultSet.next())
			{
				int id =resultSet.getInt(1);               
				String goodsName =resultSet.getString(2);          
				String introduction =resultSet.getString(3);  
				String details =resultSet.getString(4);      
				int count =resultSet.getInt(5);            
				double price =resultSet.getDouble(6);        
				int saleCount =resultSet.getInt(7);        
				String pictures =resultSet.getString(8);     
				String category =resultSet.getString(9);  
				
				goods = new Goods();
				
				goods.setId(id);
				goods.setName(name);
			    goods.setIntroduction(introduction);
			    goods.setDetails(details);
			    goods.setCount(count);
			    goods.setPrice(price);
			    goods.setSaleCount(saleCount);
			    goods.setPictures(pictures);
				goods.setCategory(category);
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
		DBUtil.close(connection);
		return goods;
	}

	@Override
	public void update(Goods goods) {
		Connection connection = DBUtil.open();
		String sql = "update goods set introduction=?,details=?,count=?,price=?,saleCount=?,pictures=?,category=? where name='"+
		goods.getName()+"';";
		
		try {
			PreparedStatement statement = (PreparedStatement) connection.prepareStatement(sql);
			statement.setString(1,goods.getIntroduction() );
			statement.setString(2, goods.getDetails());
			statement.setInt(3, goods.getCount());
			statement.setDouble(4, goods.getPrice());
			statement.setInt(5, goods.getSaleCount());
			statement.setString(6, goods.getPictures());
			statement.setString(7, goods.getCategory());
			statement.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
		DBUtil.close(connection);
	}

	@Override
	public ArrayList<Goods> list() {
		ArrayList<Goods> list = new ArrayList<Goods>();
		
		Connection connection = DBUtil.open();
		String sql = "select * from goods;";
		try {
			Statement statement = (Statement) connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			while(resultSet.next())
			{
				int id =resultSet.getInt(1);               
				String goodsName =resultSet.getString(2);          
				String introduction =resultSet.getString(3);  
				String details =resultSet.getString(4);      
				int count =resultSet.getInt(5);            
				double price =resultSet.getDouble(6);        
				int saleCount =resultSet.getInt(7);        
				String pictures =resultSet.getString(8);     
				String category =resultSet.getString(9);  
				
				Goods goods = new Goods();
				
				goods.setId(id);
				goods.setName(goodsName);
			    goods.setIntroduction(introduction);
			    goods.setDetails(details);
			    goods.setCount(count);
			    goods.setPrice(price);
			    goods.setSaleCount(saleCount);
			    goods.setPictures(pictures);
				goods.setCategory(category);
				
				list.add(goods);
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
		DBUtil.close(connection);
		return list;
	}



}
