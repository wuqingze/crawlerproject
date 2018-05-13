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
    <div class="container" style="background-color:rgb(248,248,248)">
	<div class="row clearfix">
		<div class="col-md-12 column">
			<jsp:include page="navigationBar.jsp"></jsp:include>
			<jsp:include page="goodsList.jsp"></jsp:include>
		</div>
		
	</div>
</div>
  </body>
</html>
