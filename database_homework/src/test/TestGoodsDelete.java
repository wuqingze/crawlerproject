package test;

import bean.Goods;
import daoImp.GoodsADFULImp;

public class TestGoodsDelete {

	public static void main(String[] args) {
		Goods goods = new Goods();
		goods.setName("lang");
		GoodsADFULImp goodsADFULImp = new GoodsADFULImp();
		goodsADFULImp.delete(goods);
	}

}
