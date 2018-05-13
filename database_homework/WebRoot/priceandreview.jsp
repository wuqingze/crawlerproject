<%@page import="bean.D_Goods"%>
<%@page import="org.apache.taglibs.standard.tag.common.core.ForEachSupport"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>login page</title>
    
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
   <script>
   
   function modi()
   {
      document.getElementById("hello").value="afafj";
   }
   </script>
    <style type="text/css">
    
    #xx
    {
    	margin:30px 0px 0px 0px;
    }
   </style>
  </head>
  
  <body>
  <%
  		D_Goods goods = (D_Goods)request.getAttribute("goods");
		String productTitle = goods.getProductTitle();
	 	String price = goods.getPrice();
	 	String description = goods.getDescription();
		String picture = goods.getPicture();
		String review = goods.getReview();
		
		if(price.length() == 0)
		{
			price = "¥100";
		}
  		
  		if(picture.length() == 0)
  		{
  			picture = "pictures/iphone.png";
  		}
  		
   %>
  	<div class="container">
		<div class="row clearfix">
			<div class="col-md-6 column">
				<img id="xx" src="<%=picture %>" />
			</div>
			<div class="col-md-6 column">
				<div class="jumbotron">
					<h3>
						<%=productTitle %>
					</h3>
					<p>
						<%=description %>
					</p>
					<p>
						 <a class="btn btn-primary btn-large" href="#">价钱：<%=price %></a>
					</p>
				</div>
			</div>
		</div>
		
		<div class="row clearfix">
		<div class="col-md-12 column">
		    <h1>评论</h1>
		    <br></br>
			<ul >
			<%
				String[] reviews = review.split(" ");
				for(String str: reviews)
				{
					if(str.trim().length()>0)
					{
					
					%>
				<li>
					<%=str %>
				</li>
				
			 <% 	}
			  }%>
			</ul>
		</div>
	</div>
	</div>
  </body>
</html>
