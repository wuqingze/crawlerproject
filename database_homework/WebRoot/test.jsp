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
  </head>
  
  <body>
  <div class="container">
	<div class="row clearfix">
		<div class="col-md-12 column">
			<div class="row clearfix">
				<div class="col-md-3 column">
				</div>
				<div class="col-md-6 column">
					<div class="jumbotron">
						<h1 class="page-header">
						用户登陆
					</h1>
					<form class="form-horizontal" role="form" action="<%= path%>/servlet/Login" method="post">
						<div class="form-group">
							 
							<label for="inputEmail3" class="col-md-3 control-label">
								用户名
							</label>
							<div class="col-md-9" >
								<input type="text" class="form-control" name="username" placeholder="emeil/username"/>
							</div>
						</div>
						<div class="form-group">
							 
							<label for="inputPassword3" class="col-md-3 control-label">
								密码
							</label>
							<div class="col-md-9">
								<input type="password" class="form-control" name="password" placeholder="password"/>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
								<div class="checkbox">
									 
									<label>
										<input type="checkbox" /> Remember me
									</label>
								</div>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
								 
								<button type="submit" class="btn btn-primary">
									登陆
								</button>
							</div>
							<div class="col-sm-offset-9" col-sm-3>
								<a href="<%=path%>/register.jsp">新用户注册</a>
							</div>
						</div>
					</form>
					</div>
				</div>
				<div class="col-md-3 column">
				</div>
			</div>
		</div>
	</div>
</div>
  </body>
</html>
