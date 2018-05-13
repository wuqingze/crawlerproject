<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'nevagister.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" href="css\\bootstrap.min.css" />
	<script type="text/javascript" src="js\\jquery-3.1.1.min.js" ></script>
	<script type="text/javascript" src="js\\bootstrap.min.js"></script>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
		<script type="text/javascript">
		function setSearch()
		{
			document.getElementById("isAllGoods").value = "All";
		}
	  </script>
	  
  </head>
  
  <body>
  	<div class="row clearfix">
				<div class="col-md-12 column">
					<nav class="navbar navbar-default" role="navigation">
				<div class="navbar-header">
					 <button class="navbar-toggle" type="button" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"> <span class="sr-only">Toggle navigation</span><span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span></button> <a class="navbar-brand" href="<%=path%>/servlet/ToGoodsServlet">购物网站</a>
				</div>
				<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
					<ul class="nav navbar-nav">
						<li class="active">
						    <a href="<%=path%>/AdministratorLogin.jsp">后台</a>
						</li>
						<li>
							 <a href="<%=path%>/servlet/D_OrdersServlet">订单</a>
						</li>
					</ul>
					<form class="navbar-form navbar-left" role="search" action="<%=path%>/servlet/D_FindGoodsServlet" method="post">
						<div class="form-group">
							<input type="text" class="form-control" name="goodsName" placeholder="搜索商品">
							<input type="hidden" value="notAll" name="AllGoods"id="isAllGoods"/>
						</div> <button type="submit" class="btn btn-default">搜索</button><button type="submit" class="btn btn-default" onclick='setSearch()' style="margin:0px 0px 0px 5px;">精确搜索</button>
					</form>
					<ul class="nav navbar-nav navbar-right">
					 <%
					      String username1 = (String) session.getAttribute("user");
					      if(username1 != null)
					      {
					           %>
					        <li>
							 <a ><%=username1 %></a>
						    </li>
					        <% 
					      }
					      else
					      {%>
					         <li>
							 <a href="<%=path %>">登录</a>
						</li>
						<% 
					      }%>
						<li class="dropdown">
						 <a class="dropdown-toggle" href="www.baidu.com" data-toggle="dropdown"><span class="glyphicon glyphicon-shopping-cart">&nbsp购物车</span><strong class="caret"></strong></a>
							<ul class="dropdown-menu">
								<li>
									 <form name="shoppingCar" action="<%=path%>/servlet/ShoppingCarServlet" method="post" style="margin:10px 0px 0px 20px">
											 <a onclick="toShoppingCar()">购物车</a>
									  </form>
								</li>
								<li>
									 <a href="#">Another action</a>
								</li>
								<li>
									 <a href="#">Something else here</a>
								</li>
								<li class="divider">
								</li>
								<li>
									 <a href="#">Separated link</a>
								</li>
							</ul>
						</li>
					</ul>
				</div>
			</nav>
		</div>
	</div>
  </body>
</html>
