<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>消费者管理列表</title>
<style type="text/css">
h1{text-align: center;}
table{border-collapse:collapse;border-spacing:0;border-left:1px solid #888;border-top:1px solid #888;background:#efefef;width: 800px;margin: 0 auto;}
th,td{border-right:1px solid #888;border-bottom:1px solid #888;padding:5px 15px;}
th{font-weight:bold;background:#ccc;}
</style>
</head>
<body>
<h1>新增消费者路由</h1>
<form action="/mq/consumer/saveRouting" method="post">
<input type="hidden" name="cid" value="${cid}">
<table>
	<tr>
		<td>tag</td>
		<td><input type="text" name="tag"> </td>
	</tr>
	<tr>
		<td>url</td>
		<td><input type="text" name="url"> </td>
	</tr>
	<tr>
		<td></td>
		<td><input type="submit" value="确定"> </td>
	</tr>
</table>
</form>
</body>
</html>