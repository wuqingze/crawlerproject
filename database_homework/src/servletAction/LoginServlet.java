package servletAction;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.User;
import daoImp.UserADFULImp;

public class LoginServlet extends HttpServlet {

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
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		if(username != null && password != null)
		{
			UserADFULImp userADFULImp = new UserADFULImp();
			User user = userADFULImp.find(username);
			if(user == null)
			{
				request.setAttribute("isUserExist", "false");
				request.getRequestDispatcher("/index.jsp").forward(request, response);
			}
			else if(user != null && !user.getPassword().equals(password))
			{
				request.setAttribute("isPasswordRight", "false");
				request.getRequestDispatcher("/index.jsp").forward(request, response);
			}
			else {
				request.setAttribute("user", username);
				HttpSession session = request.getSession();
				session.setAttribute("user", username);
				Cookie cookie = new Cookie("username", username);
				cookie.setMaxAge(60*60*24*7);
				response.addCookie(cookie);
				request.setAttribute("isLogin", "true");
				request.setAttribute("isUserExist", "true");
				request.setAttribute("isPasswordRight", "true");
				request.getRequestDispatcher("/goodsShow.jsp").forward(request, response);
			}
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
