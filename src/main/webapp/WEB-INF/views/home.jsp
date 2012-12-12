<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/thirdpty/jquery-1.5.1.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/thirdpty/jquery.form.js"></script>
</head>
<body>
<h1>
	Hello world!
</h1>

	<a href="${pageContext.request.contextPath}/getRegistePage?_flowId=register-flow">Register</a><br/>
	<jsp:include page="loginForm.jsp"/>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/users/userhelper.js"></script>
</body>
</html>
