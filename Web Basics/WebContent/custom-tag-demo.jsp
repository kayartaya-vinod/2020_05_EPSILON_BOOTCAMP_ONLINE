<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="vinod-custom-tags" prefix="vinod" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP Custom tags demo</title>
</head>
<body>

<h1>JSP Custom tags demo</h1>
<hr />

<!-- JSTL takes care of these 2 tasks -->
<!-- Task #1: create a tag handler class that can produce some output (in this  example, today's date value) -->
<!-- Task #2: create an XML file (.tld extension) in WEB-INF folder that maps tag-handler class to a custom tag (ex: <today />) -->

<!-- Task #3: in this JSP file, use the TLD and associate with the prefix 'vinod' -->
<vinod:today></vinod:today>

<div>
&copy; 2020 - All rights reserved by KVinod Inc.
</div>

</body>
</html>