<%@page import="daoImp.GoodsADFULImp"%>
<%@page import="bean.Goods"%>
<%@page import="daoImp.UserADFULImp"%>
<%@page import="dao.UserADFUL"%>
<%@page import="bean.User"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'orders.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" href="css\\bootstrap.min.css" />
	<script type="text/javascript" src="js\\jquery-3.1.1.min.js" ></script>
	<script type="text/javascript" src="js\\bootstrap.min.js"></script>
	<style>
	#ordersImg{
		width:180px;
		height:170px;
	}
	</style>
	<script>
	function toShoppingCar()
	{
		form = document.forms['shoppingCar'];
	    form.submit();
	}
    </script>
  </head>
  
  <body>
    	<div class="container">
	<div class="row clearfix">
		<div class="col-md-12 column">
		<jsp:include  page="navigationBar.jsp"/>
			<h3>
				我的订单
			</h3>
			<%
			
			 User user=null ;
							      UserADFULImp u = new UserADFULImp();
							      Cookie[] cookie = request.getCookies();
							      if(cookie.length>0)
							      {
							      boolean userExist = false;
							      for(int i=0; i<cookie.length; i++)
							      {
							      if(cookie[i].getName().equals("username"))
							      {
							            user = u.find(cookie[i].getValue());
							        userExist = true;
							      }
							      }
							      if(userExist == false)
							      {
							   
							      } 
							      }
				if(user != null)
				{
				    GoodsADFULImp g = new GoodsADFULImp();
					ArrayList<Goods> goodsList = user.getOrders();
					Iterator iterator = goodsList.iterator();
					int goodsIndex = 0;
				
					for(int j=goodsList.size()-1; j>=0; j--)
					{
					    Goods tempGoods = g.find(((Goods)goodsList.get(j)).getName());
					//while(iterator.hasNext())
					//{
					 //   Goods tempGoods = g.find(((Goods)iterator.next()).getName());
			 %>
			<div class="row clearfix ">
				<div class="col-md-12 column well">
					<div class="row clearfix ">
						<div class="col-md-12 column">
						    <h3>
						    	已支付
						    </h3>
						    <hr style="color:#000;">
							<p>
							<%=tempGoods.getIntroduction() %>				
							</p>
						</div>
					</div>
					<div class="row clearfix">
						<div class="col-md-12 column">
							<div class="row clearfix">
								<div class="col-md-2 column">
									<img id="ordersImg" alt="140x140" src="Goods\\<%=tempGoods.getName()%>\\<%=tempGoods.getName()+"1"%>.png" />
								</div>
								<div class="col-sm-offset-1 col-md-4 column">
									<p style="margin:50px;">
									<span class="glyphicon glyphicon-gbp"><strong style="color:#ff9933;">&nbsp<%=tempGoods.getPrice() %></strong> </span>
		
									</p>
									<p style="margin:50px;">
									<span class="glyphicon glyphicon-user"><strong style="color:#ff9933;">&nbsp<%=user.getUsername() %></strong> </span>
									</p>
								</div>
								<div class="col-md-4 column">
								</div>
								<div class="col-md-2 column">
								     <form action="<%=path %>/servlet/OrderDeleteServlet" method="post">
								     <input type="hidden" name="deletingGoods" value="<%=j%>"></input>
									 <button type="submit" class="btn btn-danger" style="margin:60px">删除</button>
									 </form>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<% 
			    goodsIndex++;} 
			          } %>
		</div>
	</div>
</div>
  </body>
</html>
