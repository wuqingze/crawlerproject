<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'AdministratorLogin.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" href="css\\bootstrap.min.css" />
	<script type="text/javascript" src="js\\jquery-3.1.1.min.js" ></script>
	<script type="text/javascript" src="js\\bootstrap.min.js"></script>
	<style>
		
		body{
			  background:#fff url("pictures\\background\\loginBackground2.jpg") no-repeat;
			  background-size: 100%;
			}
		</style>

  </head>
  
  <body>
   <div class="container">
	<div class="row clearfix">
		<div class="col-md-12 column">
		<div style="margin:100px;">
		<div class="col-md-offset-3 col-md-6">
		
		<div class="jumbotron">
		<h3 style="margin:-20px;">商品不在数据库中，搜索失败！</h3>
			<form role="form" action="<%=path%>/servlet/ShowServlet" method="post">
			  
		      <button type="submit" class="btn btn-info" style="margin:100px 0px 0px 100px;">返回</button>
			</form>
			</div>
			</div>
			</div>
		</div>
	</div>
</div>
  </body>
</html>
