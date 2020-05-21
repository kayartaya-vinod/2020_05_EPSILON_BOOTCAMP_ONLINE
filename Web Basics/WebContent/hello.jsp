<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Hello, JSP!</title>

<link rel="stylesheet" type="text/css" href="https://bootswatch.com/4/darkly/bootstrap.min.css">
</head>
<body>

<h1 class="alert alert-primary">Hello from JSP!</h1>

<div class="container">

<hr>
<p>Message from Vinod Kumar</p>

<%

int num = 123;
int limit = 25;
for(int i=1; i<=limit; i++) {
	out.println(num + " X " + i + " = " + ( i*num ) + "<br>");
}
%>

</div>
</body>
</html>