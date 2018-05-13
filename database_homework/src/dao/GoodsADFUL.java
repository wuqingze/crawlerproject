package dao;

import java.awt.List;
import java.util.ArrayList;
import java.util.LinkedList;

import bean.*;

public interface GoodsADFUL {
	public void add(Goods goods);
	public void  delete(Goods goods);
	public Goods find(String name);
	public void update(Goods goods);
	public ArrayList<Goods> list();
}
