package test;

import java.util.ArrayList;

import bean.Goods;
import bean.User;
import daoImp.UserADFULImp;

public class TestUserUpdate {

	public static void main(String[] args) {
		UserADFULImp userADFULImp = new UserADFULImp();
		User user = userADFULImp.find("nongxiaolang@foxmail.com");
		System.out.println(user.getPassword());
//		user.setUsername("nong");
//		user.setPassword("123456");
		String orders = "backpack@coffee@glasses@iphone@jackets@jeans@thinkingInJava@thinkpad";
		String[] temp = orders.split("@");
		ArrayList<Goods> tempList = new ArrayList<Goods>();
		for(int i=0; i<temp.length; i++)
		{
			Goods tempGoods = new Goods();
			tempGoods.setName(temp[i]);
			tempList.add(tempGoods);
			System.out.println(temp[i]);
		}
		user.setShoppingCar(tempList);
		userADFULImp.update(user);
	}

}
