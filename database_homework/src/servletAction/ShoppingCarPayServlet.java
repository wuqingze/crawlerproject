package servletAction;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.User;
import daoImp.UserADFULImp;

public class ShoppingCarPayServlet extends HttpServlet {

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
		String username = new String();
		UserADFULImp userADFULImp = new UserADFULImp();
		System.out.println(request.getParameter("deletingGoodsList"));
		String[] deletingGoodsList = request.getParameter("deletingGoodsList").split("@");
		boolean isLogin = false;
		for(int i=0; i<cookies.length; i++)
		{
			if(cookies[i].getName().equals("username"))
			{
				username = cookies[i].getValue();
				isLogin = true;
				break;
			}
		}
		if(isLogin)
		{
			User user = userADFULImp.find(username);
			for(int i=0; i<deletingGoodsList.length; i++)
			{   
				user.getOrders().add(user.getShoppingCar().get(Integer.parseInt(deletingGoodsList[i])));
				user.getShoppingCar().remove(Integer.parseInt(deletingGoodsList[i]));
			}
			userADFULImp.update(user);
			request.getRequestDispatcher("/orders.jsp").forward(request, response);
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