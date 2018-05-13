package test;

import java.util.ArrayList;
import java.util.Iterator;

import bean.Goods;
import bean.User;
import daoImp.GoodsADFULImp;
import daoImp.UserADFULImp;

public class TestGoodsList {

	public static void main(String[] args) {
//		GoodsADFULImp goodsADFULImp = new GoodsADFULImp();
//		ArrayList<Goods> goodsList = goodsADFULImp.list();
//		
//		Iterator<Goods> iterator = goodsList.iterator();
//		
//		while(iterator.hasNext())
//		{
//			Goods temp = iterator.next();
//			System.out.println(temp.getId()+","+temp.getName()+","+temp.getIntroduction()+","+temp.getDetails()+","
//			+temp.getCount()+","+temp.getPrice()+","+temp.getSaleCount()+","+temp.getPictures()+","+temp.getCategory());
//			
//		}
		UserADFULImp userADFULImp = new UserADFULImp();
		User user = userADFULImp.find("nongxiaolang@foxmail.com");
		ArrayList<Goods> goodsList = user.getShoppingCar();
		Iterator iterator = goodsList.iterator();
		GoodsADFULImp goodsADFULImp = new GoodsADFULImp();
		int goodsIndex = 0;
		while(iterator.hasNext())
		{ 
		     Goods temp1 = (Goods) iterator.next();
		  //   Goods goods = goodsADFULImp.find(temp1.getName());
		     System.out.println(temp1.getName());
		}
	}

}
