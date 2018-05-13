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
    <base href="http://localhost:8080/shoppingWeb/">
    
    <title>My JSP 'backStage.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" href="css\\bootstrap.min.css" />
	<script type="text/javascript" src="js\\jquery-3.1.1.min.js" ></script>
	<script type="text/javascript" src="js\\bootstrap.min.js"></script>
    <script type="text/javascript">
    function getGoodsPrice(goodsPriceId)
    {
        var price = document.getElementById(goodsPriceId);
        return price;
    }
    function getFileName()
    {
       alert("添加商品成功")
       var file = document.getElementById("inputfile").value;
       var fileName=document.getElementById("filePath");
       fileName.value=file;
    }
    function updateGoodsServlet(formName)
    {
         alert("修改成功");
         var form = document.forms[formName];
         form.submit();
    }
    function addGoodsSucess()
    {
        alert("添加商品成功")
    }
    </script>
  <script>"undefined"==typeof CODE_LIVE&&(!function(e){var t={nonSecure:"8123",secure:"8124"},c={nonSecure:"http://",secure:"https://"},r={nonSecure:"127.0.0.1",secure:"gapdebug.local.genuitec.com"},n="https:"===window.location.protocol?"secure":"nonSecure";script=e.createElement("script"),script.type="text/javascript",script.async=!0,script.src=c[n]+r[n]+":"+t[n]+"/codelive-assets/bundle.js",e.getElementsByTagName("head")[0].appendChild(script)}(document),CODE_LIVE=!0);</script></head>
  <% GoodsADFULImp goodsADFULImp = new GoodsADFULImp();
     UserADFULImp userADFULImp = new UserADFULImp();
     ArrayList<User> userList = userADFULImp.list();
     ArrayList<Goods> goodsList = goodsADFULImp.list();
     Iterator iterator = goodsList.iterator();
     Iterator userListIterator = userList.iterator();
   %>
  <body data-genuitec-lp-enabled="false" data-genuitec-file-id="wc1-0" data-genuitec-path="/shoppingWeb/WebRoot/backStage.jsp">
    <div class="container" data-genuitec-lp-enabled="false" data-genuitec-file-id="wc1-0" data-genuitec-path="/shoppingWeb/WebRoot/backStage.jsp">
    <div class="row clearfix">
       <div class="col-md-offset-1 col-md-10 column">
       <img style="width:950px;height:160px;" src="pictures\\background\\backStageBackgroundPicture.jpg"></img>
       </div>
    </div>
    <div style="height:25px;"></div>
    
    <div class="row clearfix">
		<div class="col-md-offset-1 col-md-10 column">
		    <div style="height:10px;"></div>
			<div class="tabbable" id="tabs-177866">
				<ul class="nav nav-tabs">
				<% String whichActive = (String)request.getAttribute("whichActive"); 
				  String[] active= new String[3];
				  if(whichActive == null)
				  {
				     active[0]="active";
				  }else{
				     if(whichActive.equals("AddGoods")){
				     active[0]="active";}
				     else if(whichActive.equals("ModifyGoods")){
				     active[1]="active";}
				     else{
				     active[2]="active";}
				  }
                  %>
					<li class="<%=active[0]%>">
						 <a href="#panel-814995" data-toggle="tab">添加商品</a>
					</li>
					<li class="<%=active[1]%>">
						 <a href="#panel-86423" data-toggle="tab">修改商品</a>
					</li>
					<li class="<%=active[2]%>">
					    <a href="#panel-12345" data-toggle="tab">管理用户</a>
					</li>
				</ul>
				<div class="tab-content">
					<div class="tab-pane <%=active[0] %>" id="panel-814995">
					    <div style="height:10px;"></div>
						<div class="row clearfix">
								<div class="row clearfix">
									<div class="col-md-12 column">
										<div class="row clearfix">
						                   <form action="<%=path%>/servlet/AddGoodsServlet" method="post">				
											<div class="col-md-7 column">
											<input style="margin:0px 0px 0px 20px;" type="text" class="form-control" id="name" placeholder="请输入名称">
											</div>
											<div class="col-md-3 column">
												<input style="margin:5px;width:90%;" type="file" name="addingGoodsFile" id="inputfile">
											</div>
											<div class="col-md-2 column">
											
											<input id="filePath" name="filePath" type="hidden"></input>
											<button onclick="getFileName()"style="margin:5px 0px 0px 25px;width:50px;height:30px;" type="submit" class="btn btn-danger ">添加</button>
											</div>
											</form>
										</div>
									</div>
								</div>					
							</div>
					</div>
					<div class="tab-pane <%=active[1] %>" id="panel-86423">
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
							简介
						</th>
						<th>
							价格
						</th>
						<th></th>
						<th></th>
					</tr>
				</thead>
				<tbody>
				<% while(iterator.hasNext()){
				   Goods tempGoods = (Goods) iterator.next();
				 %>
					<tr class="info">
						<td>
							<%=tempGoods.getId() %>
						</td>
						<td>
							<%=tempGoods.getName() %>
						</td>
						<td>
							<%=tempGoods.getIntroduction() %>
						</td>
						<td>
						    <form action="<%=path %>/servlet/UpdateGoodsServlet" method="post" name="<%=tempGoods.getName()%>">
						    <input type="hidden" name="updatingGoodsName" value="<%=tempGoods.getName()%>"></input>
							<input name="updatingGoodsPrice" type="text" value="<%=tempGoods.getPrice() %>"></input>
							</form>
						</td>
						<td>
						    <form action="<%=path %>/servlet/DeleteGoodsServlet" method="post">
						    <input type="hidden" name="deletingGoodsname" value="<%=tempGoods.getName()%>"></input>
							<button type="submit" style="width:50px;height:30px;" class="btn btn-danger">删除</input>
							</form>
						</td>
						<td>
							<button onclick="updateGoodsServlet('<%=tempGoods.getName() %>')" style="width:50px;height:30px;" class="btn btn-danger" >修改</input>
							
						</td>
					</tr>
				
					<%} %>
				</tbody>
			</table>
		</div>
	</div>
					</div>
				    <div class="tab-pane <%=active[2] %>" id="panel-12345">
						<div class="row clearfix">
		                   <div class="col-md-12 column">
		                       <table class="table">
				<thead>
					<tr>
						<th>
							编号
						</th>
						<th>
							用户
						</th>
						<th>
							邮件
						</th>
						<th>
							地址
						</th>
						<th>
						</th>
					</tr>
				</thead>
				<tbody>
				<% while(userListIterator.hasNext()){
				   User tempUser = (User)userListIterator.next();
				
				 %>
					<tr>
					<tr class="warning">
						<td>
							<%=tempUser.getId() %>
						</td>
						<td>
							<%=tempUser.getUsername() %>
						</td>
						<td>
							<%=tempUser.getEmail() %>
						</td>
						<td>
							<%=tempUser.getAddress() %>
						</td>
						<td>
						    <form action="<%=path%>/servlet/DeletingUserServlet" method="post">
						     <input type="hidden" name="deletingUer" value="<%=tempUser.getUsername()%>"></input>
							<button type="submit" class="btn btn-danger">删除用户</button>
							</form>
						</td>
					</tr>
					<%} %>
				</tbody>
			</table>
				           </div>
				        </div>
				    </div>
				</div>
			</div>
		</div>
	</div>
  </body>
</html>
                          

