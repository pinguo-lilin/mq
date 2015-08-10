<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>${title}</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <h2>${title}</h2>
    <a href="user/add">新增</a>
    <fieldset>
    
    <table border="1px" background="green">
	    <c:forEach items="${userList}" var="user">
	    	<tr>
	    		<td>${user.id}</td>
	    		<td>${user.name}</td>
	    		<td>${user.age}</td>
	    		<td>${user.content}</td>
	    	</tr>
		</c:forEach>
    </table>
    </fieldset>
  </body>
</html>
