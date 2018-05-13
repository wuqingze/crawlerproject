package action;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Customer;
import bean.Orders;
import daoImp.FindOrders;

/**
 * Servlet implementation class D_OrdersServlet
 */
@WebServlet("/D_OrdersServlet")
public class D_OrdersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public D_OrdersServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		Customer customer = (Customer)session.getAttribute("customer");
		String sql = "select * from orders where customerID="+customer.getCustomerID()+";";
		List<Orders> list = getOrders();
		request.setAttribute("ordersList", list);
		request.getRequestDispatcher("/D_orders.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private List<Orders> getOrders()
	{
		List<Orders> list = new ArrayList<Orders>();
		Orders orders = new Orders();
		orders.setData("2017-06-19");
		orders.setNumber("8");
		orders.setPrice("￥248.00");
		orders.setTotalPrice("1984.0   ");
		orders.setProductTitle(" 美泊客 太阳能车载空气净化器 除甲醛 PM2.5异味 汽车内用负离子香薰氧吧 抗雾霾 黑色   ");
		list.add(orders);
		
		Orders orders1 = new Orders();
		orders.setData("2017-06-19");
		orders.setNumber("9 ");
		orders.setPrice("￥79.00 ");
		orders.setTotalPrice(" 711.0 ");
		orders.setProductTitle("DK儿童人体百科全书（儿童探索世界，从了解自我开始）");
		list.add(orders);
		
		Orders orders2 = new Orders();
		orders.setData("2017-06-19");
		orders.setNumber("2");
		orders.setPrice("￥138.00 ");
		orders.setTotalPrice(" 152.0");
		orders.setProductTitle(" 美国艾罗伯特（iRobot）智能擦地机器人 Braava381 ");
		list.add(orders);
		list.add(orders1);
		list.add(orders2);
		return list; 
	}

}
