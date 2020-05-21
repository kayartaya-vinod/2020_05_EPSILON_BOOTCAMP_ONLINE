<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL Demo</title>
</head>
<body>
	<h1>JSTL Demo</h1>
	<hr>
	<%
		String[] names = {"Vinod", "Shyam", "Rohit", "Naveen", "Vijay"};
		pageContext.setAttribute("nameList", names);
	%>
	
	<c:if test="${nameList==null || fn:length(nameList)==0 }">
		<h3>There are no names!</h3>
	</c:if>

	<c:if test="${nameList!=null && fn:length(nameList)>0 }">
		<p>
			<b>Names are: </b>
		</p>
		<ul>
			<c:forEach items="${ nameList }" var="name">
				<li>${name}</li>
			</c:forEach>
		</ul>
	</c:if>

</body>
</html>