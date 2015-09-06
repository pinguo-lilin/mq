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
table{border-collapse:collapse;border-spacing:0;border-left:1px solid #888;border-top:1px solid #888;background:#efefef;margin: 0 auto;}
th,td{border-right:1px solid #888;border-bottom:1px solid #888;padding:5px 15px;}
th{font-weight:bold;background:#ccc;}
</style>
</head>
<body>
<h1>消费者管理列表</h1>
	<table border="1px" background="green" style="width:100%;">
		<tr>
			<td>编号</td>
			<td>名称</td>
			<td>类型</td>
			<td>订阅</td>
			<td>路由</td>
			<td>操作</td>
		</tr>
		<c:forEach items="${consumerList}" var="consumer">
			<tr>
				<td>${consumer.id}</td>
				<td>${consumer.name}</td>
				<td>${consumer.type}</td>
				<td>
					<table style="width:300px;">
						<c:forEach items="${consumer.subscribes}" var="subscribe">
							<tr>
								<td>${subscribe.topic}</td>
								<td>${subscribe.tag}</td>
							</tr>
						</c:forEach>
					</table>
				</td>
				<td>
					<table style="width:300px;">
						<c:forEach items="${consumer.routings}" var="routing">
							<tr>
								<td>${routing.tag}</td>
								<td>${routing.url}</td>
							</tr>
						</c:forEach>
					</table>
				</td>
				<td><a href="/mq/consumer/addSubscribe/${consumer.id}"
					target="_blank">新增订阅</a> | <a
					href="/mq/consumer/addRouting/${consumer.id}" target="_blank">新增路由</a>
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>