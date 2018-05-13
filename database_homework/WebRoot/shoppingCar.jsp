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
    
    <title>My JSP 'shopingCar.jsp' starting page</title>
    
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
	function toShoppingCar()
	{
		form = document.forms['shoppingCar'];
	    form.submit();
	}
	
	function dealBox()
	{
		var inputs = document.getElementsByTagName("input");//获取所有的input标签对象
		var checkboxArray = [];//初始化空数组，用来存放checkbox对象。
		for(var i=0;i<inputs.length;i++)
		{
	 		var obj = inputs[i];
	 		if(obj.type=='checkbox')
	 		{
	    	    checkboxArray.push(obj);
	    	}
	    }
     	for(var i=0; i<checkboxArray.length; i++)
     	{
     		checkboxArray[i].checked = true;
     	}
    }
    
    function deleteGoods(formName)
    {
        form = document.forms[formName];
        form.submit();
    }
    function pay()
    {
      
       var inputs = document.getElementsByTagName("input");
       var checkboxArray = [];
       for(var i=0;i<inputs.length;i++)
		{
	 		var obj = inputs[i];
	 		if(obj.type=='checkbox')
	 		{
	 		    if(obj.checked == true)
	 		    {
	 		    	checkboxArray.push(obj);
	 		    }
	    	}
	    }
	    var deletingGoodsList= new String();
	    for(var i=0; i<checkboxArray.length; i++)
	    {
	    	var temp = checkboxArray[i].value+'@';
	    	deletingGoodsList+=temp;
	    }
	    dGoods = document.getElementById('deletingGoodsList');
	    dGoods.value=deletingGoodsList;
	    alert("支付成功")
	    form = document.forms['payForm'];
	    form.submit();
    }
	</script>
  </head>
  
  <body>
    <div class="container">
	<div class="row clearfix">
		<div class="col-md-12 column">
			<jsp:include  page="navigationBar.jsp"/>
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
			
			
			 %>	
			<div class="row clearfix">
				<div class="col-md-12 column">
					<div class="row clearfix">
						<div class="col-md-1 column">
							
							 <button type="button" class="btn btn-default" style="margin:20px 0px 0px -10px;" onclick="dealBox()">选中全部</button>
							
						</div>
						<div class="col-md-3 column">
							<h3>
								商品图片
							</h3>
						</div>
						<div class="col-md-2 column">
							<h3>
								单价
							</h3>
						</div>
						<div class="col-md-2 column">
							<h3>
								介绍
							</h3>
						</div>
						<div class="col-md-2 column">
							<h3>
								数量
							</h3>
						</div>
						<div class="col-md-2 column">
							<h3>
								操作
							</h3>
						</div>
					</div>
				</div>
			</div>
			<%
			ArrayList<Goods> goodsList = user.getShoppingCar();
			Iterator iterator = goodsList.iterator();
			GoodsADFULImp goodsADFULImp = new GoodsADFULImp();
			int goodsIndex = 0;
			while(iterator.hasNext())
			{ 
			     Goods temp1 = (Goods) iterator.next();
			     if(temp1.getName() !=null &&!temp1.getName().equals(""))
			     {
			     
			     Goods goods = goodsADFULImp.find(temp1.getName());
			 %>
			<div class="row clearfix">
				<div class="col-md-12 column">
					<div class="row clearfix">
						<div class="col-md-1 column ">							
							 <input type="checkbox" class="checkbox" style="width:25px;height:25px;" value="<%=goodsIndex%>"></input>
						 </div>					
						 <div class="col-md-3 column">
						 	<div class="row clearfix">
								<div class="col-md-4 column">								
									<img  src="Goods\\<%=goods.getName()%>\\<%=goods.getName()+"1"%>.png" style="width:120px;height:100px"/>
									</div>								
							</div>
						</div>
						<div class="col-md-2 column ">				   
							<p style="margin:50px 0px 0px -1px;"><span class="label label-danger">¥<%=goods.getPrice() %></span>		</p>							
						</div>
						<div class="col-md-2 column ">
							<p style="margin:10px 0px 0px -10px">
								<%=goods.getIntroduction() %> 
							</p>							
						</div>
						<div class="col-md-2 column " style="margin:25px 0px 0px -20px;">					
							 <button type="button" class="btn btn-default"><span class="glyphicon-minus"></span></button>
							 <input type="text" style="width: 25%;" value="1"/>
							 <button type="button" class="btn btn-default"><span class="glyphicon-plus"></span></button>						
						</div>
						<div class="col-md-2 column " style="margin:10px;">	
						<form name="<%=goods.getName() %>" action="<%=path%>/servlet/ShoppingCarDelete" method="post">	
						    <input type="hidden" name="deletingGoods" value="<%=goodsIndex%>"></input>				
							<p>
								<a onclick="deleteGoods('<%=goods.getName() %>')">删除商品</a>
							</p>	
						</form>						
						</div>
					</div>
                    <div style="width:100%;height:10px;"></div>
                    <hr>
				</div>
			</div>
		    <%}
		    goodsIndex++;} %>
		    
		<div class="row clearfix">
		<div class="col-md-12 column">
			<div class="jumbotron">
				<div class="col-md-3 column pull-right">
				    <form name="payForm" action="<%=path %>/servlet/ShoppingCarPayServlet">
				    <input type="hidden" name="deletingGoodsList" id="deletingGoodsList" ></input>
					<button type="button" class="btn btn-danger " onclick="pay()">提交支付</button>
					</form>
				</div>
			</div>
		</div>
	    </div>
	    
		</div>
	</div>
</div>
  </body>
</html>
