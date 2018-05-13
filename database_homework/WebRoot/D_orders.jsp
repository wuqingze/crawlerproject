<%@page import="bean.Orders"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'D_orders.jsp' starting page</title>
    
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
  </head>
  
  <body>
   <div class="container">
	<div class="row clearfix">
		<div class="col-md-12 column">
			<div class="jumbotron">
				<div class="row clearfix">
		<div class="col-md-12 column">
			<table class="table">
				<thead>
					<tr>
						<th>
							编号
						</th>
						<th>
							产品
						</th>
						<th>
							交付时间
						</th>
						<th>
							单价
						</th>
						<th>
							总额
						</th>
						<th>
							数量
						</th>
					</tr>
				</thead>
				<tbody>
				<%
					List<Orders> ordersList = (List<Orders>)request.getAttribute("ordersList");
					if(ordersList != null)
					{
					
					for(int i=0; i<ordersList.size() && i<3; i++)
					{
						Orders orders = ordersList.get(i);
				 %>
					<tr class="warning">
						<td>
							<%=i %>
						</td>
						<td>
							<%=orders.getProductTitle() %>
						</td>
						<td>
							<%=orders.getData() %>
						</td>
						<td>
							<%=orders.getPrice() %>
						</td>
						<td>
							<%=orders.getTotalPrice() %>
						</td>
						<td>
							<%=orders.getNumber() %>
						</td>
					</tr>
				<%}
				} %>
				</tbody>
			</table>
		</div>
	</div>
			</div>
		</div>
	</div>
</div>
</html>
