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

   	<script type="text/javascript">
		function topriceandreview(goodName)
		{
			var form = document.forms[goodName];
			form.submit();
		}
		
		  
	   function modi()
	   {
	      document.getElementById("hello").value="afafj";
	   }
	  </script>
	  
   <style type="text/css">
   
    #xx
    {
    	margin:0px 0px 0px -75px;
    }
   </style>
  </head>
  
  <body>
  <%
 	   Boolean isFirstSearchGoods = (Boolean) session.getAttribute("isFirstSearchGoods");
 	   if(isFirstSearchGoods)
 	   {
 	   %>
 	   		<div class="row clearfix">
				<div class="col-md-12 column">
					<div class="jumbotron">
						<h2>
							亲爱的客户，你可以在搜索框输入商品名称
						</h2>
					</div>
				</div>
			</div>
 	   <% 
 	   }
 	   else
 	   {
 	   		ArrayList<D_Goods> searchResult = (ArrayList<D_Goods>) request.getAttribute("searchResult");
 	   		if(searchResult != null)
 	   		{
	 	   		if(searchResult.size()>0)
	 	   		{
	 	   		%>
	 	   			<div class="row clearfix" id="xx">
						<ul style="list-style-type:none;">
	 	   			
	 	   		<% 	int goodsSize = searchResult.size();
	 	   			for(int i=0; i<goodsSize/4+1; i++)
	 	   			{
	 	   			%>	<li>
	 	   			<div>
	 	   			<ul style="list-style-type:none;">
	 	   			<% 	for(int j=0;(i*4+j+1)<=goodsSize&&j<4; j++)
	 	   				{
	 	   						D_Goods goods = searchResult.get(i*4+j);
								String productTitle = goods.getProductTitle();
								String picture = goods.getPicture();
	 	   						if(picture.length() == 0)
	 	   						{
	 	   							picture = "pictures/iphone.png";
	 	   						}
	 	   						
	 	   				%><li>
								
									<div class="col-md-3 column">
										<form id="<%=goods.getGoodsID() %>" action="<%=path%>/servlet/D_ToPriceAndReview" method="post" >
										<img onclick="topriceandreview('<%=goods.getGoodsID()%>')" style="width:240px;height:240px;" src="<%=picture %>" />
										<div style="height:50px;"><a><p onclick="topriceandreview('<%=goods.getGoodsID()%>')">
											 <%=productTitle %>
										</p></a></div>
										<input type="hidden" name="goodsID" value="<%=goods.getGoodsID() %>" />
										</form>
									</div>
							</li>
	 	   				<%
	 	   				}
	 	   				%></ul>	</div><br></br>
	 	   				</li>
	 	   			<%}
	       %>
					</ul>
			</div>
		 <%     }
		 		else
		 		{%>
		 			<div class="row clearfix">
						<div class="col-md-12 column">
							<div class="jumbotron">
								<h2>
									没有此类商品
								</h2>
							</div>
						</div>
					</div>
		 		<%}
		 	}
	   } %>
  </body>
</html>
