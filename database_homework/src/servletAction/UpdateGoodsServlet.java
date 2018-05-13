package servletAction;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Goods;
import daoImp.GoodsADFULImp;

public class UpdateGoodsServlet extends HttpServlet {

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
		 String updatingGoodsName = request.getParameter("updatingGoodsName");
         String updatingGoodsPrice = request.getParameter("updatingGoodsPrice");
         System.out.println(updatingGoodsName+","+updatingGoodsPrice);
		 GoodsADFULImp goodsADFULImp = new GoodsADFULImp();
		 Goods goods = goodsADFULImp.find(updatingGoodsName);
		
		 if(goods != null)
		 {
			 goods.setPrice(Double.parseDouble(updatingGoodsPrice));
			 goodsADFULImp.update(goods);
		 }
		 
		 request.setAttribute("whichActive", "ModifyGoods");
		request.getRequestDispatcher("/backStage.jsp").forward(request, response);
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
