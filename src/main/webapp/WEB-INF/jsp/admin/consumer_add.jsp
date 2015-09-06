<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加消费者</title>
<style type="text/css">
h1{text-align: center;}
table{border-collapse:collapse;border-spacing:0;border-left:1px solid #888;border-top:1px solid #888;background:#efefef;width: 800px;margin: 0 auto;}
th,td{border-right:1px solid #888;border-bottom:1px solid #888;padding:5px 15px;}
th{font-weight:bold;background:#ccc;}
</style>
</head>
<body>
<h1>添加消费者</h1>
<form action="/mq/consumer/save" method="post">
	<table>
		<tr>
			<td>名称</td>
			<td><input type="text" name="name"></td>
			<td>(只能是字母)</td>
		</tr>
		<tr>
			<td>类型</td>
			<td>
				<select name="type" style="height: 28px;">
					<option value="1">pushConsumer</option>
					<option value="2">pullConsumer</option>
					<option value="3">orderConsumer</option>
				</select>
			</td>
			<td></td>
		</tr>
		<tr>
			<td></td>
			<td><input type="submit" name="确定"></td>
			<td></td>
		</tr>
	</table>
</form>
</body>
</html>