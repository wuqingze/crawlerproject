package test;

import bean.Goods;
import daoImp.GoodsADFULImp;

public class TestGoodsUpdate {

	public static void main(String[] args) {
		Goods goods = new Goods();
		goods.setId(2);
		goods.setName("nong");
		GoodsADFULImp goodsADFULImp = new GoodsADFULImp();
		goodsADFULImp.update(goods);
	}

}
