package servletAction;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Goods;
import bean.User;
import daoImp.GoodsADFULImp;
import daoImp.UserADFULImp;

public class ToShoppingCarServlet extends HttpServlet {

	/**
		 * The doGet method of the servlet. <br>
		 *
		 * This method is called when a form has its tag value method equals to get.
		 * 
		 * @param request the request send by the client to the server
		 * @param response the response send by the server to the client
		 * @throws ServletException if an error occurred
		 * @throws IOException if an error occurred
		 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cookie[] cookies = request.getCookies();
		UserADFULImp userADFULImp = new UserADFULImp();
		GoodsADFULImp goodsADFULImp = new GoodsADFULImp();
		User user =  new User();
		boolean isLogin = false;
		for(int i=0; i<cookies.length; i++)
		{
			if(cookies[i].getName().equals("username"))
			{
				user = userADFULImp.find(cookies[i].getValue());
				isLogin = true;
				break;
			}
		}
		if(isLogin)
		{
			String toShoppingCarGoodsName = request.getParameter("toShoppingCarGoodsName");
			Goods goods = goodsADFULImp.find(toShoppingCarGoodsName);
			ArrayList<Goods> goodsList = user.getShoppingCar();
			goodsList.add(goods);
			userADFULImp.update(user);
			request.getRequestDispatcher("/shoppingCar.jsp").forward(request, response);
		}
		else {
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}
	}

	/**
		 * The doPost method of the servlet. <br>
		 *
		 * This method is called when a form has its tag value method equals to post.
		 * 
		 * @param request the request send by the client to the server
		 * @param response the response send by the server to the client
		 * @throws ServletException if an error occurred
		 * @throws IOException if an error occurred
		 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
