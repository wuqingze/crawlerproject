package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import bean.Goods;
import daoImp.GoodsADFULImp;

public class TestGoodsAdd {

	public static void main(String[] args) {                
		        
		
		String[] message = new String[8];
		int i =0;
		File file = new File("WebRoot//Goods//camera/bean.txt");
	    try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String tempstrString = reader.readLine();
			while(tempstrString!= null)
			{
				String[] userfulstring = tempstrString.split("=");
				message[i] = userfulstring[1];
				System.out.println(message[i]);
				i++;
				tempstrString = reader.readLine();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    String name = message[0];             
		String introduction = message[1];     
		String details = message[2];          
		int count = Integer.parseInt(message[3]);               
		double price = Double.parseDouble(message[4]);            
		int saleCount = Integer.parseInt(message[5]);           
		String pictures = message[6];         
		String category = message[7];
		Goods goods = new Goods(name, introduction, details, count, price, saleCount, pictures, category);
		GoodsADFULImp goodsADFULImp = new GoodsADFULImp();
		goodsADFULImp.add(goods);
		System.out.println(goods.getId());
	}

}
