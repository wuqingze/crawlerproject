package action;

import daoImp.UserVerify;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class D_LoginServlet
 */
@WebServlet("/D_LoginServlet")
public class D_LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public D_LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		boolean userIsNotNull = UserVerify.find(username);
		if(userIsNotNull)
		{
			HttpSession session = request.getSession();
			session.setAttribute("customer", UserVerify.getCustomer(username));
			session.setAttribute("user", username);
			session.setAttribute("isFirstSearchGoods", new Boolean(true));
			request.getRequestDispatcher("/goodsPage.jsp").forward(request, response);
		}
		else
		{
			request.getRequestDispatcher("/Login.jsp").forward(request, response);	
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
