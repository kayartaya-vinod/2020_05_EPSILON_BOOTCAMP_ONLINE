<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>EL Demo</title>
<link rel="stylesheet" type="text/css" href="https://bootswatch.com/4/darkly/bootstrap.min.css">

</head>
<body>

<h1>EL Demo</h1>
<hr>

<div>
Value of 10 + 20 is ${10+20}
</div>
<div>
<!-- 'myname' is looked in different scopes, if found will be used, else will be blank -->
<!-- Different scopes are pageScope, requestScope, sessionScope, applicationScope -->
<!-- These scopes are represented by these implicit objects: pageContext, request, session, application -->

<%
// typically, these will be not used in JSPs while EL and JSTL are being used.
pageContext.setAttribute("myname", "Vinod Kumar");
request.setAttribute("myname", "Vinod");
session.setAttribute("myname", "Kumar Vinod");
application.setAttribute("myname", "Vinod Kumar Kayartaya");
%>

My name is ${ myname } <br>
My name is ${ applicationScope.myname } <br>
My name is ${ sessionScope.myname } <br>
My name is ${ requestScope.myname } <br>
My name is ${ pageScope.myname } <br>
</div>

<div>
<form action="">
<input class="form-control" type="text" name="username" placeholder="enter your name and press ENTER / RETURN key...">
</form>
</div>

<h3>Hello, ${ param.username == null ? 'friend' : param.username } !!</h3>

<div>
The value of the 'greet' cookie is ${ cookie.greet.value }
</div>
</body>
</html>











