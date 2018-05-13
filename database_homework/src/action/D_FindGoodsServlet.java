package action;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.enterprise.inject.New;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.D_Goods;
import bean.GoodsComparator;
import daoImp.FindGoods;


/**
 * Servlet implementation class D_FindGoodsServlet
 */
@WebServlet("/D_FindGoodsServlet")
public class D_FindGoodsServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public D_FindGoodsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String AllGoods = request.getParameter("AllGoods");
		if ("notAll".equals(AllGoods))
		{
			String goodsName = request.getParameter("goodsName");
			
			if(goodsName != null)
			{
				String[] tags = goodsName.split(" ");
				HashMap<String, GoodsComparator> hashMap = new HashMap<String, GoodsComparator>();
				ArrayList<D_Goods> goodsList = new ArrayList<D_Goods>();
				
				for (String string : tags)
				{
					ArrayList<D_Goods> list = FindGoods.find("select * from Goods where goodsID in (select goodsID from Tag where tagName='"+string+"');");
					for (D_Goods d_Goods : list)
					{
						if(!hashMap.containsKey(d_Goods.getGoodsID()))
						{
							GoodsComparator goodsC = new GoodsComparator();
							goodsC.setGoods(d_Goods);
							goodsC.setWeight(1);
							hashMap.put(d_Goods.getGoodsID(), goodsC);
						}
						else
						{
							hashMap.get(d_Goods.getGoodsID()).setWeight(hashMap.get(d_Goods.getGoodsID()).getWeight()+1);
						}
					}
				}
				
				ArrayList<GoodsComparator> temp = new ArrayList<GoodsComparator>();
				Iterator<Entry<String, GoodsComparator>> i = hashMap.entrySet().iterator();
				while(i.hasNext())
				{
					Map.Entry entry = (Map.Entry) i.next();
					GoodsComparator C = (GoodsComparator) entry.getValue();
					temp.add(C);
				}
				
				temp.sort(new Comparator<GoodsComparator>()
				{

					@Override
					public int compare(GoodsComparator o1, GoodsComparator o2)
					{
						if(o1.getWeight()>o2.getWeight())
						{
							return -1;
						}
						else if (o1.getWeight()<o2.getWeight())
						{
							return 1;
						}
						else
						{
							return 0;
						}
					}
				});
				
				for (GoodsComparator goodsComparator : temp)
				{
					goodsList.add(goodsComparator.getGoods());
				}
				
				HttpSession session = request.getSession();
				session.setAttribute("isFirstSearchGoods", new Boolean(false));
				request.setAttribute("searchResult", goodsList);
				request.getRequestDispatcher("/goodsPage.jsp").forward(request, response);
			}
		}
		else if ("All".equals(AllGoods)) 
		{
			String goodsName = request.getParameter("goodsName");
			
			if(goodsName != null)
			{
				String[] tags = goodsName.split(" ");
				HashMap<String, GoodsComparator> hashMap = new HashMap<String, GoodsComparator>();
				ArrayList<D_Goods> goodsList = new ArrayList<D_Goods>();
				
				for (String string : tags)
				{
					ArrayList<D_Goods> list = FindGoods.find("select * from Goods where productTitle like \"%"+string+"%\";");
					for (D_Goods d_Goods : list)
					{
						if(!hashMap.containsKey(d_Goods.getGoodsID()))
						{
							GoodsComparator goodsC = new GoodsComparator();
							goodsC.setGoods(d_Goods);
							goodsC.setWeight(1);
							hashMap.put(d_Goods.getGoodsID(), goodsC);
						}
						else
						{
							hashMap.get(d_Goods.getGoodsID()).setWeight(hashMap.get(d_Goods.getGoodsID()).getWeight()+1);
						}
					}
				}
				
				ArrayList<GoodsComparator> temp = new ArrayList<GoodsComparator>();
				Iterator<Entry<String, GoodsComparator>> i = hashMap.entrySet().iterator();
				while(i.hasNext())
				{
					Map.Entry entry = (Map.Entry) i.next();
					GoodsComparator C = (GoodsComparator) entry.getValue();
					temp.add(C);
				}
				
				temp.sort(new Comparator<GoodsComparator>()
				{

					@Override
					public int compare(GoodsComparator o1, GoodsComparator o2)
					{
						if(o1.getWeight()>o2.getWeight())
						{
							return -1;
						}
						else if (o1.getWeight()<o2.getWeight())
						{
							return 1;
						}
						else
						{
							return 0;
						}
					}
				});
				
				for (GoodsComparator goodsComparator : temp)
				{
					goodsList.add(goodsComparator.getGoods());
				}
				
				HttpSession session = request.getSession();
				session.setAttribute("isFirstSearchGoods", new Boolean(false));
				request.setAttribute("searchResult", goodsList);
				request.getRequestDispatcher("/goodsPage.jsp").forward(request, response);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
