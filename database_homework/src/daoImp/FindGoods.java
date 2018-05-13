package daoImp;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import bean.D_Goods;
import util.DBUtil;

public class FindGoods
{
	public  static ArrayList<D_Goods> find(String SQL)
	{
		ArrayList<D_Goods> list = new ArrayList<D_Goods>();
		Connection connection = DBUtil.open();
		
		String goodsID = null;
		String productTitle =null;
		String price = null;
		String description = null;
		String picture = null;
		String review = null;
		
		try
		{
			Statement statement = (Statement) connection.createStatement();
			ResultSet resultSet = statement.executeQuery(SQL);
			while(resultSet.next())
			{
				D_Goods goods = new D_Goods();
				goodsID = resultSet.getString(1);
				productTitle = resultSet.getString(2);
				price = resultSet.getString(3);
				description = resultSet.getString(4);
				picture = resultSet.getString(5);
				review = resultSet.getString(6);
				goods.setGoodsID(goodsID);
				goods.setProductTitle(productTitle);
				goods.setPrice(price);
				goods.setDescription(description);
				goods.setPicture(picture);
				goods.setReview(review);
				list.add(goods);
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		DBUtil.close(connection);
		return list;
	}
}
