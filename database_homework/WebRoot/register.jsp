<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'register.jsp' starting page</title>
    
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
		<title>login</title>
		
		<style>
		
		body{
			  background:#fff url("pictures\\background\\loginBackground2.jpg") no-repeat;
			  background-size: 100%;
			}
		</style>
		
		<style type="text/css">
			#registerPanel{
				margin: 50px;
			}
		</style>
		
		<script>
		function verify()
		{ 
		     username = document.getElementById("username");
		     if(username.value == "")
		     {
		         alert("用户名不能为空");
		     }
		     else{
		     password = document.getElementById("password");
		     password1 = document.getElementById("password1");
		     if(password.value != password1.value)
		     {
		        alert("输入的密码不一致，请重新输入");
		     }
		     else
		     {
		        if(password.value != null && password1.value!=null)
		        {
			        if(password.value == password1.value)
			        {
			            form = document.forms[0];
			            form.submit();
			        }
		        }
		        
		     }
		     }
		     
		}
		
		</script>

  </head>
  
  <body>
<%

 %>
<%
if(request.getAttribute("isUserExist")!=null){
 if(request.getAttribute("isUserExist").equals("true"))
%>
<script>
alert("用户已经存在！");
</script>
<% }
%>


    	<div  class="container">
	<div class="row clearfix">
		<div class="col-md-12 column" id="registerPanel">
			<div class="col-md-4"></div>
			<div class="col-md-4"></div>
			<div class="col-md-4" style="background-color:#EEEEEE;" >
					<h1 class="page-header">
						新用户注册
					</h1>
					<form class="form-horizontal" role="form" method="post" action="<%=path %>/servlet/RegisterServlet">
						<div class="form-group">
							 
							<label for="inputEmail3" class="col-md-3 control-label">
								用户名
							</label>
							<div class="col-md-9" >
								<input type="text" class="form-control" name="username" id="username" placeholder="emeil/username"/>
							</div>
						</div>
						<div class="form-group">
							 
							<label for="inputPassword3" class="col-md-3 control-label">
								密码
							</label>
							<div class="col-md-9">
								<input type="password" class="form-control" name="password" id="password" placeholder="password"/>
							</div>
						</div>
						<div class="form-group">
							 
							<label for="inputPassword3" class="col-md-3 control-label">
								确认密码
							</label>
							<div class="col-md-9">
								<input type="password" class="form-control" name="password1" id="password1" placeholder="password"/>
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
								 
								<button onclick="verify()" class="btn btn-primary">
									注册
								</button>
							</div>
			
						</div>
					</form>
				</div>
		</div>
	</div>
</div>
  </body>
</html>
