
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="bean.*" %>
<%@ page import="daoImp.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'pay.jsp' starting page</title>
    
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
	function paySuccess()
	{
	   alert("支付成功")
	}
	
	function toShoppingCar()
	{
		form = document.forms['shoppingCar'];
	    form.submit();
	}
	</script>
  </head>
  
  <body>
  <% 
  		String goodsName = request.getParameter("goodsName");
		GoodsADFULImp goodsADFULImp = new GoodsADFULImp();
		Goods goods = goodsADFULImp.find(goodsName);
        
        User user = null;
        Cookie[] cookie = request.getCookies();
        if(cookie.length>0)
        {
           boolean userExist = false;
      	   for(int i=0; i<cookie.length; i++)
           {
              if(cookie[i].getName().equals("username"))
                {
        			String username = cookie[i].getValue();
       				UserADFULImp u = new UserADFULImp();
       				user = u.find(username);
                }
           }
	   }				      
							      
   %>
    	<div class="container">
	<div class="row clearfix">
		<div class="col-md-12 column">
			<jsp:include  page="navigationBar.jsp"/>
			<div class="row clearfix">
				<div class="col-md-12 column">
					<h3>
						确认收货地址
					</h3> <address><strong><%=user.getAddress() %></strong></address>
				</div>
			</div>
			<div class="row clearfix">
				<div class="col-md-12 column">
					<div class="row clearfix">
						<div class="col-md-6 column">
							<img alt="140x140" src="Goods\\<%=goods.getName()%>\\<%=goods.getName()+"1"%>.png" />
								<h4><%=goods.getIntroduction() %></h4>
						</div>
						<div class="col-md-6 column jumbotron">
							<table class="table">
								<thead>
									<tr>
										<th>
											产品属性
										</th>
										<th>
											运送方式
										</th>
										<th>
											交付时间
										</th>
										<th>
											单价
										</th>
										<th>
											数量
										</th>
										<th>
											总价
										</th>
									</tr>
								</thead>
								<tbody>
									
									<tr class="success">
										<td>
											<%=goods.getCategory() %>
										</td>
										<td>
											顺丰快递
										</td>
										<td>
											<% 
											Date d = new Date();
											String[] temp = d.toString().split(" ");
											String time = temp[1]+"/"+temp[2]+"/"+temp[5];
											%>
											<%=time %>
										</td>
										<td>
											<%=goods.getPrice() %>
										</td>
										<td>
											1
										</td>
										<td>
											<%=goods.getPrice() %>
										</td>
									</tr>
									
								</tbody>
							</table>
							<div class="warning">
							<form action="<%=path%>/servlet/OrdersServlet" method="post">
							     <input type="hidden" name="toOrdersGoodsName" value="<%=goods.getName() %>"></input>
								 <button class="btn btn-danger" type="submit" onclick="paySuccess()">提交订单</button>
							</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
  </body>
</html>
