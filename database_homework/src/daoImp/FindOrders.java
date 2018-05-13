package daoImp;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import bean.D_Goods;
import bean.Orders;
import util.DBUtil;

public class FindOrders
{
	public  static ArrayList<Orders> find(String SQL)
	{
		ArrayList<Orders> list = new ArrayList<Orders>();
		Connection connection = DBUtil.open();
		
		try
		{
			Statement statement = (Statement) connection.createStatement();
			ResultSet resultSet = statement.executeQuery(SQL);
			while(resultSet.next())
			{
				Orders orders = new Orders();
				orders.setData(resultSet.getString("orderDate"));
				orders.setNumber(resultSet.getString("goodsNum"));
				orders.setPrice(resultSet.getString("goodsPrice"));
				orders.setTotalPrice(resultSet.getString("totalPrice"));
				String pString = "select * from goods where goodsid in (select goodsID from orders where receiverName  = '"+resultSet.getString("receiverName")+"');";
				ResultSet r = statement.executeQuery(pString);
				if(r.next())
				{
					orders.setProductTitle(r.getString("productTitle "));
				}
				list.add(orders);
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		DBUtil.close(connection);
		return list;
	}
}

