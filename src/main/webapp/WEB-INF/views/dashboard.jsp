<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User's dashboard page</title>
</head>
<body>	
	<h2>Welcome ${user.firstName}</h2> <br/>
	<c:forEach items="${userdomains}" var="userTemplate">
			<a href="${pageContext.request.contextPath}/cms/getTemplate/${userTemplate.templateId}/${userTemplate.id}">${userTemplate.domainName}</a><br/>
	</c:forEach>	
	<a href="${pageContext.request.contextPath}/getTemplates">Add templates to your account</a>
</body>
</html>