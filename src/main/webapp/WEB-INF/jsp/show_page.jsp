<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="/mq/msg/send" method="post">
	<table>
		<tr>
			<td>topic</td>
			<td><input type="text" name="topic"></td>
		</tr>
		<tr>
			<td>tag</td>
			<td><input type="text" name="tag"></td>
		</tr>
		<tr>
			<td>info</td>
			<td><input type="text" name="info"></td>
		</tr>
		<tr>
			<td>key</td>
			<td><input type="text" name="key"></td>
		</tr>
		<tr>
			<td></td>
			<td><input type="submit" value="send"></td>
		</tr>
	</table>
</form>
</body>
</html>