<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Page not found</title>
</head>
<body>

<h1>OOPS! There was an internal server error while processing request</h1>
<a href="./">Go home</a>

<%
	out.println(exception.getMessage());
%>

</body>
</html>