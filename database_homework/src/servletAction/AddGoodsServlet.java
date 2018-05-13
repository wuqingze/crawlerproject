package servletAction;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Goods;
import daoImp.GoodsADFULImp;
import daoImp.GoodsFile;

public class AddGoodsServlet extends HttpServlet {

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
		
		String filePath = request.getParameter("filePath");
		System.out.println(filePath);
		int index=0;
		for(int i=0; i<filePath.length();i++)
		{ 
			if(filePath.charAt(i)=='\\')
			{
				index = i;
			}
		}
		String fileName = new String();
		for(int i=index+1; i<filePath.indexOf('.');i++)
		{
		    fileName+=filePath.charAt(i);
		}
		
		String[] message = new String[8];
		int i =0;
		String root = request.getSession().getServletContext().getRealPath("/");
		
		File file = GoodsFile.getFile(root,fileName);
	    try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String tempstrString = reader.readLine();
			while(tempstrString!= null)
			{
				System.out.println(tempstrString);
				String[] userfulstring = tempstrString.split("=");
				message[i] = userfulstring[1];
				System.out.println(tempstrString);
				System.out.println(message[i]);
				i++;
				tempstrString = reader.readLine();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    String name = message[0];             
		String introduction = message[1];     
		String details = message[2];          
		int count = Integer.parseInt(message[3]);               
		double price = Double.parseDouble(message[4]);            
		int saleCount = Integer.parseInt(message[5]);           
		String pictures = message[6];         
		String category = message[7];
		Goods goods = new Goods(name, introduction, details, count, price, saleCount, pictures, category);
		GoodsADFULImp goodsADFULImp = new GoodsADFULImp();
		goodsADFULImp.add(goods);
		System.out.println(goods.getId());
        request.setAttribute("whichActive", "AddGoods");
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
