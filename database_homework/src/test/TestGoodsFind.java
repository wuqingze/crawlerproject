package test;

import bean.Goods;
import daoImp.GoodsADFULImp;

public class TestGoodsFind {

	public static void main(String[] args) {
		Goods goods;
		GoodsADFULImp goodsADFULImp = new GoodsADFULImp();
		goods = goodsADFULImp.find("nong");
		
		System.out.println(goods.getId());
		System.out.println(goods.getDetails());
	}

}
