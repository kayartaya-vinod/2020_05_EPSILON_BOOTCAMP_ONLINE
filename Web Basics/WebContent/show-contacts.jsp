<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>List of contacts</title>
</head>
<body>

<h1>List of contacts (You have ${fn:length(contacts)} contacts)</h1>
<hr>


<table border="1">
	
	<tr>
		<th>Name</th>
		<th>Email address</th>
		<th>Phone number</th>
		<th>City</th>
	</tr>
	
	<c:forEach items="${contacts}" var="ct">
	<tr>
		<td>${ ct.gender=="Male" ? "Mr." : "Ms." } ${ ct.name }</td>
		<td>${ ct.email }</td>
		<td>${ ct.phone }</td>
		<td>${ ct.city }</td>
	</tr>
	</c:forEach>
</table>

</body>
</html>