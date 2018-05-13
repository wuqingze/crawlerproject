
<%@page import="daoImp.GoodsADFULImp"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="bean.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>goods details</title>
    
	<link rel="stylesheet" href="css\\bootstrap.min.css" />
	<script type="text/javascript" src="js\\jquery-3.1.1.min.js" ></script>
	<script type="text/javascript" src="js\\bootstrap.min.js"></script>
   <script>
      function chageImage(url)
      {
         var bigPicture = document.getElementById("bigPicture");
         bigPicture.src = url;
      }
    
      function addGoods()
      {
        
       	 number = document.getElementById("numberOfGoods").value;
       	 document.getElementById("numberOfGoods").value = parseInt(number)+1;
      }
      
      function deleteGoods()
      {
      	number = document.getElementById("numberOfGoods").value;
       	document.getElementById("numberOfGoods").value = parseInt(number)-1;
      }
      
      function toShoppingCar()
	  {
		form = document.forms['shoppingCar'];
	    form.submit();
	  }
	
    </script>
    <style>
    #goodsImg{
    width:150px;
    height:200px;
    }
    
    </style>

  </head>
  
  <body>
   
 <div class="container">
	<div class="row clearfix">
		<div class="col-md-12 column">
			<jsp:include page="navigationBar.jsp"></jsp:include>
			<%
		
			String goodsName = request.getParameter("goodsName");
			GoodsADFULImp goodsADFULImp = new GoodsADFULImp();
			Goods currentGoods = goodsADFULImp.find(goodsName);
			String[] pictures = currentGoods.getPictures().split("@");
			 %>
			<div class="row clearfix">
				<div class="col-md-12 column">
					<div class="row clearfix">
						<div class="col-md-7 column" ">
							<div class="row clearfix ">
								<div class="col-md-12 column">
									<img style="width:400px;height:400px"alt="140x140" src="Goods\\<%=currentGoods.getName()%>\\<%=pictures[0]%>.png" id="bigPicture">
								</div>
							</div>
							
								<div >
									<img id="goodsImg" src="Goods\\<%=currentGoods.getName()%>\\<%=pictures[0]%>.png" onmouseover="chageImage('Goods\\<%=currentGoods.getName()%>\\<%=pictures[0]%>.png')"></img>
									<img id="goodsImg" src="Goods\\<%=currentGoods.getName()%>\\<%=pictures[1]%>.png" onmouseover="chageImage('Goods\\<%=currentGoods.getName()%>\\<%=pictures[1]%>.png')"></img>
									<img id="goodsImg" src="Goods\\<%=currentGoods.getName()%>\\<%=pictures[2]%>.png" onmouseover="chageImage('Goods\\<%=currentGoods.getName()%>\\<%=pictures[2]%>.png')"></img>
									<img id="goodsImg" src="Goods\\<%=currentGoods.getName()%>\\<%=pictures[3]%>.png" onmouseover="chageImage('Goods\\<%=currentGoods.getName()%>\\<%=pictures[3]%>.png')"></img>
									
								</div>
							
						</div>
						<div class="col-md-5 column jumbotron" style="height: 550px">
							<div class="row clearfix">
								<div class="col-md-12 column">
									<h3>
										<%=currentGoods.getName() %>
									</h3>
									<p>
									    <%=currentGoods.getIntroduction() %>
									</p>
								</div>
							</div>
							<div class="row clearfix">
								<div class="col-md-12 column">
									<ul class="list-unstyled">
										<li>
											单价：<span class="label label-danger"><%=currentGoods.getPrice() %></span>
										</li><br>
										<li>
											存货：<span class="label label-success"><%=currentGoods.getCount() %></span>
										</li><br>
										<li>
											已售：<span class="label label-info"><%=currentGoods.getSaleCount() %></span>
										</li>
									</ul>
									<button type="button" class="btn btn-default" onclick="deleteGoods()"><span class="glyphicon glyphicon-minus"></button>
										<input id="numberOfGoods" style="width: 40px;" value="1" />
										 <button type="button" class="btn btn-default active" onclick="addGoods()"><span class="glyphicon glyphicon-plus"></span></button> 
								</div>
							</div>
							<dir style="height:20px"></dir>
							<div class="col-md-12 column">
								<div class="col-md-6 column">
									<form role="form" class="form-group-lg" action="servlet/PayServlet" method="post">
									    <input type="hidden" name="goodsName" value="<%=currentGoods.getName() %>"/> 
										<button type="submit" class="btn btn-warning">立即购买</button>
									</form>
								</div>
								<div class="col-md-6 column">
									<form role="form" action="<%=path%>/servlet/ToShoppingCarServlet" method="post">
									     <input type="hidden" value="<%=request.getParameter("goodsName") %>" name="toShoppingCarGoodsName"></input>
									    
										 <button type="submit" class="btn btn-danger" style="margin:5px;height:30px"><span class="glyphicon glyphicon-shopping-cart">加入购物车</button>
							    </form>
								</div>
							</div>
						</div>
					</div>
					<div class="row clearfix jumbotron">
						<div class="col-md-12 column">
							<%
							String[] goodsDetails = currentGoods.getDetails().split("@");
							int detailsIndex =0;
							for(int i=0; i<4; i++)
							{
								for(int j=0; j<4; j++,detailsIndex++)
								{
									if(i*4+j<goodsDetails.length)
									{
										%>
										<div  class="col-md-3 column">
										<p><%=goodsDetails[detailsIndex] %></p>
										</div>
							<%       }
							    }
							} %>
							
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

    
  </body>
</html>