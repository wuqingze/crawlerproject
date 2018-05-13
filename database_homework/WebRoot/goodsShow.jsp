<%@page import="daoImp.GoodsADFULImp"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="daoImp.*" %>
<%@ page import="bean.*" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
     <title>goods showing</title>
    
	<link rel="stylesheet" href="css\\bootstrap.min.css" />
		<script type="text/javascript" src="js\\jquery-3.1.1.min.js" ></script>
		<script type="text/javascript" src="js\\bootstrap.min.js"></script>
		<script type="text/javascript">
		function setGoodsName(goodName)
		{
			var form = document.forms[goodName];
			form.submit();
		}
		
		function toShoppingCar()
		{
			form=document.forms['shoppingCar'];
		    form.submit();		
		}
		function search()
		{ 
		  alert("search");
		  form = document.forms['search'];
		  form.submit();
		
		}
		</script>
		<style>
		#imgHW
		{
		 width:250px;
		 height:260px;
		}
		
		</style>
  </head>
  
  <body >
    <div class="container" style="background-color:rgb(248,248,248)">
	<div class="row clearfix">
		<div class="col-md-12 column">
			<jsp:include page="navigationBar.jsp"></jsp:include>
			<div class="row clearfix" >
				<div class="col-md-12 column">
				<% 
				    GoodsADFULImp goodsImp = new GoodsADFULImp();
					ArrayList<Goods> allGoods = goodsImp.list();
					int goodsIndex =0;
 					for(int i=0; i<allGoods.size()/4+1;i++)
 					{
				
				%>
					<div class="row clearfix">
						<% for(int j=0; j<4; j++,goodsIndex++){ 
						   if((i*4+j)<allGoods.size())
						   {
						   Goods currentGoods  = allGoods.get(goodsIndex);
						   String[] pictures = currentGoods.getPictures().split("@");
						
						%>
						<div class="col-md-3 column" >
						<form id="<%=currentGoods.getName() %>" action="<%=path %>/servlet/DetailsServlet" method="post">
							<div class="carousel slide"  id="carousel<%=i %><%=j %>">
								<ol class="carousel-indicators">
									<li class="active" data-slide-to="0" data-target="#carousel<%=i %><%=j %>">
									</li>
									<li data-slide-to="1" data-target="#carousel<%=i %><%=j %>">
									</li>
									<li data-slide-to="2" data-target="#carousel<%=i %><%=j %>">
									</li>
								</ol>
								<div class="carousel-inner">
									<div class="item active">
										<img id="imgHW" alt="" src="Goods\\<%=currentGoods.getName()%>\\<%=pictures[0]%>.png" onclick="setGoodsName('<%=currentGoods.getName()%>')"/>
									</div>
									<div class="item">
										<img id="imgHW" alt="" src="Goods\\<%=currentGoods.getName()%>\\<%=pictures[1]%>.png" onclick="setGoodsName('<%=currentGoods.getName()%>')"/>
									</div>
									<div class="item">
										<img id="imgHW" alt="" src="Goods\\<%=currentGoods.getName()%>\\<%=pictures[2]%>.png" onclick="setGoodsName('<%=currentGoods.getName()%>')"/>
									</div>
									<div class="item">
										<img id="imgHW" alt="" src="Goods\\<%=currentGoods.getName()%>\\<%=pictures[3]%>.png" onclick="setGoodsName('<%=currentGoods.getName()%>')"/>
									</div>
								</div> <a class="left carousel-control" href="#carousel<%=i %><%=j %>" data-slide="prev"><span class="glyphicon glyphicon-chevron-left"></span></a> <a class="right carousel-control" href="#carousel<%=i %><%=j %>" data-slide="next"><span class="glyphicon glyphicon-chevron-right"></span></a>
							</div>
							<p>¥<%=currentGoods.getPrice() %><% for(int blank=0; blank<15;blank++){%> &nbsp<%}%>
							 <%=currentGoods.getSaleCount() %>人付款</p>
							<p><a onclick="setGoodsName('<%=currentGoods.getName() %>')"><%=currentGoods.getIntroduction() %></a></p>
						<input type="hidden" name="goodsName" value="<%=currentGoods.getName() %>"/> 
						</form>
						</div>
					    <%} }%>
					</div>
					<div style="width:100%;height:20px"></div>
				<%}%>
				</div>
			</div>
		</div>
		
	</div>
</div>
  </body>
</html>